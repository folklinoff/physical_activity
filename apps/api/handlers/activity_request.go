package handlers

import (
	"coconut/model"
	"net/http"
	"strconv"

	"github.com/gin-gonic/gin"
	"gorm.io/gorm"
)

// ref: https://github.com/swaggo/swag/blob/master/example/celler/controller/accounts.go
type ActivityRequestHandler struct {
	db *gorm.DB
}

func NewActivityRequestHandler(db *gorm.DB) *ActivityRequestHandler {
	return &ActivityRequestHandler{
		db: db,
	}
}

func (h *ActivityRequestHandler) Register(e *gin.Engine) {

}

// @Summary Get all items
// @Description Get all items
// @Accept  json
// @Produce  json
// @Success 200 {object} JSONResult{activities=[]model.ActivityRequest}
// @Router /activity_request [get]
func (h *ActivityRequestHandler) Get(c *gin.Context) {
	var activities struct {
		Activities []model.ActivityRequest
	}
	h.db.Find(&activities.Activities)
	c.JSON(http.StatusOK, activities)
}

// @Summary Get one item
// @Description Get one item
// @Accept  json
// @Produce  json
// @Param   id     path    int     true        "ID"
// @Success 200 {object} model.ActivityRequest
// @Failure 400 {string} string "400 StatusBadRequest"
// @Failure 404 {string} string "404 not found"
// @Router /activity_request/{id} [get]
func (h *ActivityRequestHandler) GetOne(c *gin.Context) {
	id, err := strconv.Atoi(c.Param("id"))
	if err != nil {
		c.JSON(http.StatusBadRequest, err.Error())
		return
	}
	var t model.ActivityRequest
	h.db.First(&t, id)
	c.JSON(http.StatusOK, t)
}

// @Summary Add item
// @Description Add item
// @Accept  json
// @Produce  json
// @Param role body model.ActivityRequest true "data"
// @Success 200 {object} model.ActivityRequest
// @Router /activity_request [post]
func (h *ActivityRequestHandler) Post(c *gin.Context) {
	var t model.ActivityRequest
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
// @Param role body model.ActivityRequest true "data"
// @Param   id     path    int     true        "ID"
// @Success 200 {object} model.ActivityRequest
// @Failure 400 {string} string "400 StatusBadRequest"
// @Failure 404 {string} string "404 not found"
// @Router /activity_request/{id} [put]
func (h *ActivityRequestHandler) Put(c *gin.Context) {
	id, err := strconv.Atoi(c.Param("id"))
	if err != nil {
		c.JSON(http.StatusBadRequest, err.Error())
		return
	}

	var t model.ActivityRequest
	if err := c.ShouldBindJSON(&t); err != nil {
		c.JSON(http.StatusBadRequest, err.Error())
		return
	}
	t.ID = uint64(id)
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
// @Router /ActivityRequest/{id} [delete]
func (h *ActivityRequestHandler) Delete(c *gin.Context) {
	id, err := strconv.Atoi(c.Param("id"))
	if err != nil {
		c.JSON(http.StatusBadRequest, err.Error())
		return
	}
	h.db.Delete(&model.ActivityRequest{}, id)
	c.JSON(http.StatusNoContent, gin.H{})
}
