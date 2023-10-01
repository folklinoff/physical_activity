package model

type Goal struct {
	ID uint64 `gorm:"goal_id,primarykey,autoIncrement" json:"id"`

	Name   string `json:"name"`
	Points int64  `json:"points"`
}
