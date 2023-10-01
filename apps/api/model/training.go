package model

type Training struct {
	ID uint64 `gorm:"training_id,primarykey,autoIncrement" json:"id"`

	Date       string `json:"date" gorm:"date"`
	EmployeeId uint   `json:"employee_id" gorm:"employee_id"`
}
