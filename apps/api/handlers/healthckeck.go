package handlers

import (
	"net/http"

	"github.com/gin-gonic/gin"
)

// ref: https://swaggo.github.io/swaggo.io/declarative_comments_format/api_operation.html
// @Summary Healthcheck
// @Description get string by ID
// @Tags Healthcheck
// @Accept  json
// @Produce  json
// @Success 200 {object} msg
// @Router /healthcheck [get]
func Healthcheck(c *gin.Context) {
	c.JSON(http.StatusOK, msg{Message: "Server is up and running!"})
}

type msg struct {
	Message string `json:"message"`
}
