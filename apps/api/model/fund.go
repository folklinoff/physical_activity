package model

type Fund struct {
	ID             uint64 `gorm:"fund_id,primarykey,autoIncrement" json:"id"`
	Name           string `gorm:"name" json:"name"`
	Password       string `json:"password"`
	FounderName    string `gorm:"founder_name" json:"founder_name"`
	FoundationDate string `gorm:"foundation_date" json:"foundation_date"`
	Points         int64  `json:"points"`
	Goals          []Goal `gorm:"many2many:funds_goals"`
}
