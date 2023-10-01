package myutil

import (
	"fmt"
	"strconv"

	"github.com/golang-jwt/jwt"
)

var secret []byte = []byte("secret")

func CreateToken(userID uint64, userType string) (string, error) {
	token := jwt.NewWithClaims(jwt.SigningMethodHS256, jwt.MapClaims{
		userType: strconv.FormatInt(int64(userID), 10),
	})
	return token.SignedString(secret)
}

func Auth(tokenString string, userType string) (uint64, error) {
	token, err := jwt.Parse(tokenString, func(token *jwt.Token) (interface{}, error) {
		return secret, nil
	})
	if err != nil {
		return 0, err
	}

	if claims, ok := token.Claims.(jwt.MapClaims); ok && token.Valid {
		strId := claims[userType]
		if strId == nil {
			return 0, fmt.Errorf("incorrect auth token")
		}
		id, err := strconv.ParseInt(strId.(string), 10, 64)

		if err != nil {
			return 0, err
		}
		return uint64(id), nil
	}

	return 0, err
}
