package handlers

import (
	"coconut/model"
	myutil "coconut/pkg/util"
	"math/rand"
	"net/http"
	"strconv"
	"time"

	"github.com/gin-gonic/gin"
	"gorm.io/gorm"
)

type EmployeeHandler struct {
	db            *gorm.DB
	imageUploader ImageUploader
}

type ImageUploader interface {
	PresignPutUrl(key string, lifetime int64) (string, error)
	PresignGetUrl(key string, contentType string) (string, error)
}

func NewEmployeeHandler(db *gorm.DB, imageUploader ImageUploader) *EmployeeHandler {
	return &EmployeeHandler{
		db:            db,
		imageUploader: imageUploader,
	}
}

func (h *EmployeeHandler) Register(e *gin.Engine) {
	e.GET("/employee", h.Get)
	e.GET("/employee/:id", h.GetOne)
	e.GET("/employee/data", h.GetData)
	e.POST("/employee", h.Post)
	e.PUT("/employee", h.Put)
	e.POST("/employee/login", h.Login)
	e.POST("/employee/activity", h.NewAction)
	e.PUT("/employee/points", h.PutStorePointsWay)
}

// @Summary Получить список всех сотрудников
// @Description Получить список всех сотрудников
// @Tags Employees
// @Accept  json
// @Produce  json
// @Success 200 {object} JSONResult{employees=[]model.Employee}
// @Router /employee [get]
func (h *EmployeeHandler) Get(c *gin.Context) {
	var employees struct {
		Employees []model.Employee `json:"employees"`
	}
	h.db.Preload("Joggings").
		Preload("Awards").
		Preload("Events").
		Preload("Trainings").
		Preload("Walks").
		Find(&employees.Employees)
	c.JSON(http.StatusOK, employees)
}

// @Summary Получить информацию о сотруднике
// @Description Получить информацию о сотруднике
// @Tags Employees
// @Accept  json
// @Produce  json
// @Param   id     path    int     true        "ID"
// @Success 200 {object} model.Employee
// @Failure 400 {string} string "400 StatusBadRequest"
// @Failure 404 {string} string "404 not found"
// @Router /employee/{id} [get]
func (h *EmployeeHandler) GetOne(c *gin.Context) {
	id, err := strconv.Atoi(c.Param("id"))
	if err != nil {
		c.JSON(http.StatusBadRequest, err.Error())
		return
	}
	var employee model.Employee
	h.db.Preload("Joggings").
		Preload("Awards").
		Preload("Events").
		Preload("Trainings").
		Preload("Walks").
		First(&employee, id)
	c.JSON(http.StatusOK, employee)
}

// @Summary Получить информацию о сотруднике
// @Description Получить информацию о сотруднике
// @Tags Employees
// @Accept  json
// @Produce  json
// @Param   token     path    string     true        "token"
// @Success 200 {object} model.Employee
// @Failure 400 {string} string "400 StatusBadRequest"
// @Failure 404 {string} string "404 not found"
// @Router /employee/data [get]
func (h *EmployeeHandler) GetData(c *gin.Context) {
	token := c.Request.Header.Get("Authorization")
	id, err := myutil.Auth(token, "employee")
	if err != nil {
		c.JSON(http.StatusBadRequest, err.Error())
		return
	}
	var employee model.Employee
	h.db.Preload("Joggings").
		Preload("Awards").
		Preload("Events").
		Preload("Trainings").
		Preload("Walks").
		First(&employee, id)
	c.JSON(http.StatusOK, employee)
}

// @Summary Регистрация сотрудника в системе
// @Description Регистрация сотрудника в системе
// @Tags Employees
// @Accept  json
// @Produce  json
// @Param role body model.Employee true "data"
// @Success 200 {object} model.Employee
// @Router /employee [post]
func (h *EmployeeHandler) Post(c *gin.Context) {
	var t model.Employee
	if err := c.ShouldBindJSON(&t); err != nil {
		c.JSON(http.StatusBadRequest, err.Error())
		return
	}
	h.db.Preload("Joggings").
		Preload("Awards").
		Preload("Events").
		Preload("Trainings").
		Preload("Walks").
		Save(t)
	c.JSON(http.StatusOK, gin.H{})
}

// @Summary Обновление информации о сотруднике в системе
// @Description Обновление информации о сотруднике в системе
// @Tags Employees
// @Accept  json
// @Produce  json
// @Param role body model.Employee true "data"
// @Success 200 {object} model.Employee
// @Failure 400 {string} string "400 StatusBadRequest"
// @Failure 404 {string} string "404 not found"
// @Router /employee/{id} [put]
func (h *EmployeeHandler) Put(c *gin.Context) {
	token := c.Request.Header.Get("Authorization")
	id, err := myutil.Auth(token, "employee")
	if err != nil {
		c.JSON(http.StatusBadRequest, err.Error())
		return
	}

	var t model.Employee
	if err := c.ShouldBindJSON(&t); err != nil {
		c.JSON(http.StatusBadRequest, err.Error())
		return
	}
	t.ID = id

	h.db.Preload("Joggings").
		Preload("Awards").
		Preload("Events").
		Preload("Trainings").
		Preload("Walks").
		Save(&t)
	c.JSON(http.StatusOK, gin.H{})
}

// @Summary Аутентификация сотрудника
// @Description Delete item
// @Tags Employees
// @Accept  json
// @Produce  json
// @Param   role body model.Employee true "data"
// @Success 200	{object} JSONResult{message=string}
// @Failure 400 {object} JSONResult{error=string}
// @Failure 404 {string} string "404 not found"
// @Router /employee/login [post]
func (h *EmployeeHandler) Login(c *gin.Context) {
	var t model.Employee
	if err := c.ShouldBindJSON(&t); err != nil {
		c.JSON(http.StatusBadRequest, err.Error())
		return
	}
	var dbval model.Employee
	h.db.Where("phone_number = ?", t.PhoneNumber).First(&dbval)
	if t.Password != dbval.Password {
		c.JSON(http.StatusBadRequest, gin.H{"error": "password don't match"})
		return
	}
	token, err := myutil.CreateToken(dbval.ID, "employee")
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}
	c.Header("Authorization", token)
	c.JSON(http.StatusOK, gin.H{"message": "welcome"})
}

// @Summary Уведомление об активности
// @Description Delete item
// @Tags Employees
// @Accept  json
// @Produce  json
// @Param   id     path    int     true        "ID"
// @Success 204
// @Failure 400 {string} string "400 StatusBadRequest"
// @Failure 404 {string} string "404 not found"
// @Router /employee/{id}/action [post]
func (h *EmployeeHandler) NewAction(c *gin.Context) {
	var t model.Action
	if err := c.ShouldBindJSON(&t); err != nil {
		c.JSON(http.StatusBadRequest, err.Error())
		return
	}
	downloadUrl, err := h.imageUploader.PresignGetUrl(strconv.FormatInt(rand.NewSource(time.Now().Unix()).Int63(), 10), "image/png")
	if err != nil {
		c.JSON(http.StatusBadRequest, err.Error())
		return
	}
	switch t.ActivityType {
	case "jogging":

	case "walking":

	case "training":

	}

	c.JSON(http.StatusNoContent, gin.H{
		"image_download_url": downloadUrl,
	})
}

// @Summary Выбор направления для пожертвований
// @Description Delete item
// @Tags Employees
// @Accept  json
// @Produce  json
// @Param   id     path    int     true        "ID"
// @Success 204
// @Failure 400 {string} string "400 StatusBadRequest"
// @Failure 404 {string} string "404 not found"
// @Router /employee/points [put]
func (h *EmployeeHandler) PutStorePointsWay(c *gin.Context) {
	token := c.Request.Header.Get("Authorization")
	id, err := myutil.Auth(token, "employee")
	if err != nil {
		c.JSON(http.StatusBadRequest, err.Error())
		return
	}
	var t model.Employee
	if err := c.ShouldBindJSON(&t); err != nil {
		c.JSON(http.StatusBadRequest, err.Error())
		return
	}
	t.ID = id
	h.db.Model(t).Select("FundId", "GoalId").Updates(t)
	c.JSON(http.StatusOK, gin.H{"message": "welcome"})
}

// @Summary Вывод десяти лучших сотрудников
// @Description Delete item
// @Tags Employees
// @Accept  json
// @Produce  json
// @Param   criteria     query    string     true        "criteria"
// @Success 204
// @Failure 400 {string} string "400 StatusBadRequest"
// @Failure 404 {string} string "404 not found"
// @Router /leaderboard [get]
func (h *EmployeeHandler) Leaderboard(c *gin.Context) {
	var employees struct {
		Employees []model.Employee `json:"employees"`
	}
	h.db.Preload("Joggings").
		Preload("Awards").
		Preload("Events").
		Preload("Trainings").
		Preload("Walks").
		Order("total_point DESC").
		Limit(10).
		Find(&employees.Employees)

	c.JSON(http.StatusOK, gin.H{})
}
