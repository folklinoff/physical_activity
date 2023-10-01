package handlers

import (
	"coconut/model"
	myutil "coconut/pkg/util"
	"net/http"
	"strconv"

	"github.com/gin-gonic/gin"
	"gorm.io/gorm"
)

type FundHandler struct {
	db *gorm.DB
}

func NewFundHandler(db *gorm.DB) *FundHandler {
	return &FundHandler{
		db: db,
	}
}

func (h *FundHandler) Register(e *gin.Engine) {
	e.POST("/funds", h.Create)
	e.PUT("/funds", h.Put)
	e.GET("/funds", h.Get)
	e.GET("/funds/:id", h.GetOne)
	e.GET("/funds/data", h.GetCurrent)
	e.POST("/funds/login", h.Login)
}

// ref: https://swaggo.github.io/swaggo.io/declarative_comments_format/api_operation.html
// @Summary Создание фонда
// @Description Создание нового фонда (осуществляется самими фондами)
// @Tags Fund
// @Accept  json
// @Produce  json
// @Param request body model.Fund true "Fund model"
// @Success 200 {object} msg
// @Router /funds [post]
func (h *FundHandler) Create(c *gin.Context) {
	var t model.Fund
	if err := c.ShouldBindJSON(&t); err != nil {
		c.JSON(http.StatusBadRequest, err.Error())
		return
	}
	h.db.Preload("Goals").Save(&t)
	c.JSON(http.StatusOK, gin.H{})
}

// ref: https://swaggo.github.io/swaggo.io/declarative_comments_format/api_operation.html
// @Summary Создание фонда
// @Description Создание нового фонда (осуществляется самими фондами)
// @Tags Fund
// @Accept  json
// @Produce  json
// @Param request body model.Fund true "Fund model"
// @Success 200 {object} msg
// @Router /funds [put]
func (h *FundHandler) Put(c *gin.Context) {
	token := c.Request.Header.Get("Authorization")
	id, err := myutil.Auth(token, "fund")
	if err != nil {
		c.JSON(http.StatusBadRequest, err.Error())
		return
	}

	var t model.Fund
	if err := c.ShouldBindJSON(&t); err != nil {
		c.JSON(http.StatusBadRequest, err.Error())
		return
	}
	t.ID = id
	h.db.Preload("Goals").Save(t)
	c.JSON(http.StatusOK, gin.H{})
}

// ref: https://swaggo.github.io/swaggo.io/declarative_comments_format/api_operation.html
// @Summary Создание фонда
// @Description Создание нового фонда (осуществляется самими фондами)
// @Tags Fund
// @Accept  json
// @Produce  json
// @Param request body model.Fund true "Fund model"
// @Success 200 {object} msg
// @Router /funds/login [post]
func (h *FundHandler) Login(c *gin.Context) {
	var t model.Fund
	if err := c.ShouldBindJSON(&t); err != nil {
		c.JSON(http.StatusBadRequest, err.Error())
		return
	}
	var dbval model.Employee
	h.db.Where("name = ?", t.Name).First(&dbval)
	if t.Password != dbval.Password {
		c.JSON(http.StatusBadRequest, gin.H{"error": "password don't match"})
		return
	}
	token, err := myutil.CreateToken(dbval.ID, "fund")
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}
	c.Header("Authorization", token)
	c.JSON(http.StatusOK, gin.H{"message": "welcome"})
}

// ref: https://swaggo.github.io/swaggo.io/declarative_comments_format/api_operation.html
// @Summary Получение списка всех фондов
// @Description Получение списка всех фондов в кратком формате
// @Tags Fund
// @Accept  json
// @Produce  json
// @Success 200 {object} JSONResult{funds=[]model.Fund}
// @Router /funds [get]
func (h *FundHandler) Get(c *gin.Context) {
	var t []model.Fund
	h.db.Preload("Goals").Find(&t)
	c.JSON(http.StatusOK, t)
}

// ref: https://swaggo.github.io/swaggo.io/declarative_comments_format/api_operation.html
// @Summary Получение информации о фонде
// @Description Получение полной информации о фонде
// @Tags Fund
// @Accept  json
// @Produce  json
// @Param id path string true "Account ID"
// @Success 200 {object} model.Fund
// @Router /funds/{id} [get]
func (h *FundHandler) GetOne(c *gin.Context) {
	id, err := strconv.Atoi(c.Param("id"))
	if err != nil {
		c.JSON(http.StatusBadRequest, err.Error())
		return
	}
	var t model.Fund
	t.ID = uint64(id)
	h.db.Preload("Goals").First(&t)
	c.JSON(http.StatusOK, t)
}

// ref: https://swaggo.github.io/swaggo.io/declarative_comments_format/api_operation.html
// @Summary Получение информации о фонде
// @Description Получение полной информации о фонде
// @Tags Fund
// @Accept  json
// @Produce  json
// @Param id path string true "Account ID"
// @Success 200 {object} model.Fund
// @Router /funds/data [get]
func (h *FundHandler) GetCurrent(c *gin.Context) {
	token := c.Request.Header.Get("Authorization")
	id, err := myutil.Auth(token, "fund")
	if err != nil {
		c.JSON(http.StatusBadRequest, err.Error())
		return
	}
	var t model.Fund
	t.ID = uint64(id)
	h.db.Preload("Goals").First(&t)
	c.JSON(http.StatusOK, t)
}
