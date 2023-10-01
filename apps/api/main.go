package main

import (
	"coconut/handlers"
	"coconut/model"
	"coconut/pkg/uploader"
	"context"
	"fmt"
	"log"
	"net/http"
	"os"
	"os/signal"
	"syscall"
	"time"

	_ "coconut/docs"

	"github.com/aws/aws-sdk-go-v2/aws"
	"github.com/aws/aws-sdk-go-v2/config"
	"github.com/aws/aws-sdk-go-v2/service/s3"
	"github.com/gin-gonic/gin"
	swaggerfiles "github.com/swaggo/files"
	ginSwagger "github.com/swaggo/gin-swagger"
	"gorm.io/driver/postgres"
	"gorm.io/gorm"
)

// ref: https://swaggo.github.io/swaggo.io/declarative_comments_format/general_api_info.html
// @title АПИ сервера для корпоративной системы благотворительности
// @version 1.0
// @description Реализованный в рамках хакатона сервер для корпоративной системы благотворительности
// @termsOfService http://swagger.io/terms/

// @contact.name API Support
// @contact.url http://www.swagger.io/support
// @contact.email support@swagger.io

// @license.name Apache 2.0
// @license.url http://www.apache.org/licenses/LICENSE-2.0.html

// @host 188.72.77.222:8080
// @BasePath /

// @securityDefinitions.basic BasicAuth

// @securityDefinitions.apikey ApiKeyAuth
// @in header
// @name Authorization

// @securitydefinitions.oauth2.application OAuth2Application
// @tokenUrl https://example.com/oauth/token
// @scope.write Grants write access
// @scope.admin Grants read and write access to administrative information
func main() {
	ctx := context.Background()
	db := NewDatabase()
	err := db.AutoMigrate(
		&model.Principal{},
		&model.Charitable{},
		&model.ActivityType{},
		&model.Employee{},
		&model.Jogging{},
		&model.Award{},
		&model.Event{},
		&model.Training{},
		&model.Walk{},
		&model.Fund{},
		&model.Fund{},
		&model.Goal{},
		&model.Host{},
		&model.SportsPartner{},
		&model.Partner{},
	)
	if err != nil {
		log.Fatal(err)
	}

	customResolver := aws.EndpointResolverWithOptionsFunc(func(service, region string, options ...interface{}) (aws.Endpoint, error) {
		if service == s3.ServiceID && region == "ru-central1" {
			return aws.Endpoint{
				PartitionID:   "yc",
				URL:           "https://storage.yandexcloud.net",
				SigningRegion: "ru-central1",
			}, nil
		}
		return aws.Endpoint{}, fmt.Errorf("unknown endpoint requested")
	})

	awsCfg, err := config.LoadDefaultConfig(context.TODO(), config.WithEndpointResolverWithOptions(customResolver))
	if err != nil {
		log.Fatal(err)
	}

	// Создаем клиента для доступа к хранилищу S3
	client := s3.NewFromConfig(awsCfg)
	presigner := uploader.Presigner{PresignClient: s3.NewPresignClient(client)}
	imageUploader := uploader.NewImageUploader(presigner, "coco-bucket")

	e := gin.New()
	e.Group("/", handlers.CORSMiddleware())
	handlers.NewFundHandler(db).Register(e)
	handlers.NewPrincipalHandler(db).Register(e)
	handlers.NewEmployeeHandler(db, imageUploader).Register(e)
	handlers.NewActivityHandler(db).Register(e)
	e.GET("/swagger/*any", ginSwagger.WrapHandler(swaggerfiles.Handler))
	e.GET("/healthcheck", handlers.Healthcheck)

	srv := http.Server{
		Addr:    fmt.Sprintf(":%s", "8080"),
		Handler: e,
	}

	go func() {
		log.Printf("server started on port %s\n", "8080")
		_ = srv.ListenAndServe()
	}()
	stop := make(chan os.Signal, 1)
	signal.Notify(stop, syscall.SIGTERM, syscall.SIGINT)

	<-stop
	go func() {
		ctx, cancel := context.WithTimeout(ctx, 5*time.Second)
		defer cancel()
		_ = srv.Shutdown(ctx)
	}()

}

func NewDatabase() *gorm.DB {
	dsn := os.Getenv("DATABASE_URL")
	db, err := gorm.Open(postgres.Open(dsn), &gorm.Config{})

	if err != nil {
		log.Fatal(err)
	}
	return db
}
