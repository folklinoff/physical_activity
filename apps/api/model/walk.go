package model

type Walk struct {
	ID uint64 `gorm:"walk_id,primarykey,autoIncrement" json:"id"`

	Date       string `json:"date" gorm:"date"`
	Duration   string `json:"duration"`
	Price      string `json:"price"`
	PrizeType  string `json:"prize_type"`
	EmployeeId uint   `json:"employee_id" gorm:"employee_id"`
}
