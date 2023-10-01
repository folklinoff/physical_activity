package handlers

import (
	"coconut/model"
	"net/http"
	"strconv"

	"github.com/gin-gonic/gin"
	"gorm.io/gorm"
)

// ref: https://github.com/swaggo/swag/blob/master/example/celler/controller/accounts.go
type ActionHandler struct {
	db *gorm.DB
}

func NewActionHandler(db *gorm.DB) *ActionHandler {
	return &ActionHandler{
		db: db,
	}
}

func (h *ActionHandler) Register(e *gin.Engine) {

}

// @Summary Get all items
// @Description Get all items
// @Accept  json
// @Produce  json
// @Success 200 {object} JSONResult{activities=[]model.Action}
// @Router /action [get]
func (h *ActionHandler) Get(c *gin.Context) {
	var activities struct {
		Activities []model.Action
	}
	h.db.Find(&activities.Activities)
	c.JSON(http.StatusOK, activities)
}

// @Summary Get one item
// @Description Get one item
// @Accept  json
// @Produce  json
// @Param   id     path    int     true        "ID"
// @Success 200 {object} model.Action
// @Failure 400 {string} string "400 StatusBadRequest"
// @Failure 404 {string} string "404 not found"
// @Router /action/{id} [get]
func (h *ActionHandler) GetOne(c *gin.Context) {
	id, err := strconv.Atoi(c.Param("id"))
	if err != nil {
		c.JSON(http.StatusBadRequest, err.Error())
		return
	}
	var t model.Action
	h.db.First(&t, id)
	c.JSON(http.StatusOK, t)
}

// @Summary Add item
// @Description Add item
// @Accept  json
// @Produce  json
// @Param role body model.Action true "data"
// @Success 200 {object} model.Action
// @Router /action [post]
func (h *ActionHandler) Post(c *gin.Context) {
	var t model.Action
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
// @Param role body model.Action true "data"
// @Param   id     path    int     true        "ID"
// @Success 200 {object} model.Action
// @Failure 400 {string} string "400 StatusBadRequest"
// @Failure 404 {string} string "404 not found"
// @Router /action/{id} [put]
func (h *ActionHandler) Put(c *gin.Context) {
	_, err := strconv.Atoi(c.Param("id"))
	if err != nil {
		c.JSON(http.StatusBadRequest, err.Error())
		return
	}

	var t model.Action
	if err := c.ShouldBindJSON(&t); err != nil {
		c.JSON(http.StatusBadRequest, err.Error())
		return
	}
	// t.ID = uint64(id)

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
// @Router /action/{id} [delete]
func (h *ActionHandler) Delete(c *gin.Context) {
	id, err := strconv.Atoi(c.Param("id"))
	if err != nil {
		c.JSON(http.StatusBadRequest, err.Error())
		return
	}
	h.db.Delete(&model.Action{}, id)
	c.JSON(http.StatusNoContent, gin.H{})
}
