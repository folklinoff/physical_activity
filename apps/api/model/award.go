package model

type Award struct {
	ID         uint64 `gorm:"award_id,primarykey,autoIncrement" json:"id"`
	Name       string `json:"name" gorm:"name"`
	EmployeeId uint   `json:"employee_id" gorm:"employee_id"`
	Date       string `json:"date" gorm:"date"`
}
