package handlers

import (
	"coconut/model"
	myutil "coconut/pkg/util"
	"fmt"
	"net/http"

	"github.com/gin-gonic/gin"
	"gorm.io/gorm"
)

type PrincipalHandler struct {
	db *gorm.DB
}

func NewPrincipalHandler(db *gorm.DB) *PrincipalHandler {
	return &PrincipalHandler{
		db: db,
	}
}

func (h *PrincipalHandler) Register(e *gin.Engine) {
	e.GET("/stats", h.GetStats)
	e.POST("/admin", h.Create)
	e.POST("/admin/login", h.Login)
}

type Criterias struct {
	Departments   []string `json:"departments"`
	EmployeeIds   []int64  `json:"employee_ids"`
	ActivityTypes []string `json:"activity_types"`
	Period        []string `json:"period"`
	Daytime       []string `json:"daytime"`
	Orgs          []string `json:"orgs"`
	Goals         []string `json:"goals"`
}

// @Summary Получение статистики
// @Description Get one item
// @Accept  json
// @Tags stats
// @Produce  json
// @Param   role     body    Criterias{}     true "Principal"
// @Failure 400 {string} string "400 StatusBadRequest"
// @Failure 404 {string} string "404 not found"
// @Router /stats [get]
func (h *PrincipalHandler) GetStats(c *gin.Context) {
	var criterias Criterias
	if err := c.ShouldBindJSON(&criterias); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}

}

// @Summary Получение статистики
// @Description Get one item
// @Accept  json
// @Tags stats
// @Produce  json
// @Param   role     body    model.Principal     true "Principal"
// @Failure 400 {string} string "400 StatusBadRequest"
// @Failure 404 {string} string "404 not found"
// @Router /admin [post]
func (h *PrincipalHandler) Create(c *gin.Context) {
	var t model.Principal
	if err := c.ShouldBindJSON(&t); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}
	h.db.Save(&t)
	c.JSON(http.StatusOK, gin.H{"message": "created"})
}

// @Summary Получение статистики
// @Description Get one item
// @Accept  json
// @Tags stats
// @Produce  json
// @Param   principal     body    model.Principal     true "Pricipal"
// @Failure 400 {string} string "400 StatusBadRequest"
// @Failure 404 {string} string "404 not found"
// @Router /admin/login [post]
func (h *PrincipalHandler) Login(c *gin.Context) {
	var t model.Principal
	if err := c.ShouldBindJSON(&t); err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}

	var dbval model.Principal
	h.db.Where("email = ?", t.Email).First(&dbval)
	if t.Password != dbval.Password {
		c.JSON(http.StatusBadRequest, gin.H{"error": "password don't match"})
		return
	}
	token, err := myutil.CreateToken(dbval.ID, "admin")
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		return
	}
	c.Header("Authorization", token)
	c.JSON(http.StatusOK, gin.H{"message": "welcome"})
	h.db.Save(&t)
}

// @Summary Получение статистики
// @Description Get one item
// @Accept  json
// @Tags stats
// @Produce  json
// @Param   easy     in    model.Principal     true "Pricipal"
// @Failure 400 {string} string "400 StatusBadRequest"
// @Failure 404 {string} string "404 not found"
// @Router /evaluate [post]
func (h *PrincipalHandler) Evaluate(c *gin.Context) {
	cat := c.Param("category")
	val := c.Param("val")
	var col string
	tx := h.db.Model(&model.Action{})
	switch cat {
	case "fund":
		col = "fund_id"
		var fund model.Fund
		h.db.Find(fund, val)
		// var goals []model.Goal

	case "goal":
		col = "goal_id"
	}
	fmt.Println(col, tx)
	var actions []model.Action
	var employee model.Employee

	for _, action := range actions {
		empId := action.EmployeeID
		h.db.First(&employee, empId)

	}
}
