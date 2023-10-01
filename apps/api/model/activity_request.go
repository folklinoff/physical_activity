package model

type ActivityRequest struct {
	ID    uint64 `json:"id" gorm:"primarykey,autoIncrement"`
	Name  string `json:"name"`
	Hours int64  `json:"hours"`
}
