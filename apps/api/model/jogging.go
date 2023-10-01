package model

type Jogging struct {
	ID uint64 `gorm:"jogging_id,primarykey,autoIncrement" json:"id"`

	Date       string `json:"date" gorm:"date"`
	Duration   string `json:"duration"`
	Price      string `json:"price"`
	PrizeType  string `json:"prize_type"`
	EmployeeId int64  `json:"employee_id"`
}
