package model

type Principal struct {
	ID       uint64 `json:"id" gorm:"primaryKey,autroIncrement"`
	Email    string `json:"email"`
	Password string `json:"password"`
}
