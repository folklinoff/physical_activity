package handlers

import (
	"coconut/model"
	"net/http"
	"strconv"

	"github.com/gin-gonic/gin"
	"gorm.io/gorm"
)

// ref: https://github.com/swaggo/swag/blob/master/example/celler/controller/accounts.go
type ActivityHandler struct {
	db *gorm.DB
}

func NewActivityHandler(db *gorm.DB) *ActivityHandler {
	return &ActivityHandler{
		db: db,
	}
}

func (h *ActivityHandler) Register(e *gin.Engine) {

}

// @Summary Get all items
// @Description Get all items
// @Accept  json
// @Produce  json
// @Success 200 {object} JSONResult{activities=[]model.Activity}
// @Router /activity [get]
func (h *ActivityHandler) Get(c *gin.Context) {
	var activities struct {
		Activities []model.ActivityType
	}
	h.db.Find(&activities.Activities)
	c.JSON(http.StatusOK, activities)
}

// @Summary Get one item
// @Description Get one item
// @Accept  json
// @Produce  json
// @Param   id     path    int     true        "ID"
// @Success 200 {object} model.Activity
// @Failure 400 {string} string "400 StatusBadRequest"
// @Failure 404 {string} string "404 not found"
// @Router /activity/{id} [get]
func (h *ActivityHandler) GetOne(c *gin.Context) {
	id, err := strconv.Atoi(c.Param("id"))
	if err != nil {
		c.JSON(http.StatusBadRequest, err.Error())
		return
	}
	var t model.ActivityType
	h.db.First(&t, id)
	c.JSON(http.StatusOK, t)
}

// @Summary Add item
// @Description Add item
// @Accept  json
// @Produce  json
// @Param role body model.Activity true "data"
// @Success 200 {object} model.Activity
// @Router /activity [post]
func (h *ActivityHandler) Post(c *gin.Context) {
	var t model.ActivityType
	if err := c.ShouldBindJSON(&t); err != nil {
		c.JSON(http.StatusBadRequest, err.Error())
		return
	}
	h.db.Save(&t)
	c.JSON(http.StatusOK, gin.H{})
}

// @Summary Update item
// @Description Update item
// @Accept  json
// @Produce  json
// @Param role body model.Activity true "data"
// @Param   id     path    int     true        "ID"
// @Success 200 {object} model.Activity
// @Failure 400 {string} string "400 StatusBadRequest"
// @Failure 404 {string} string "404 not found"
// @Router /activity/{id} [put]
func (h *ActivityHandler) Put(c *gin.Context) {
	_, err := strconv.Atoi(c.Param("id"))
	if err != nil {
		c.JSON(http.StatusBadRequest, err.Error())
		return
	}

	var t model.ActivityType
	if err := c.ShouldBindJSON(&t); err != nil {
		c.JSON(http.StatusBadRequest, err.Error())
		return
	}
	h.db.Save(t)
	c.JSON(http.StatusOK, gin.H{})
}

// @Summary Delete item
// @Description Delete item
// @Accept  json
// @Produce  json
// @Param   id     path    int     true        "ID"
// @Success 204
// @Failure 400 {string} string "400 StatusBadRequest"
// @Failure 404 {string} string "404 not found"
// @Router /activity/{id} [delete]
func (h *ActivityHandler) Delete(c *gin.Context) {
	id, err := strconv.Atoi(c.Param("id"))
	if err != nil {
		c.JSON(http.StatusBadRequest, err.Error())
		return
	}
	h.db.Delete(&model.ActivityType{}, id)
	c.JSON(http.StatusNoContent, gin.H{})
}
