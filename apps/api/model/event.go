package model

type Event struct {
	ID         uint64 `gorm:"event_id,primarykey,autoIncrement" json:"id"`
	Name       string `json:"name" gorm:"name"`
	HostId     int64  `json:"host_id" gorm:"host_id"`
	StartDate  string `json:"start_date" gorm:"start_date"`
	EndDate    string `json:"end_date" gorm:"end_date"`
	Prize      int64  `json:"prize" gorm:"prize"`
	WinnerId   int64  `json:"winner_id" gorm:"winner_id"`
	EmployeeId uint   `json:"employee_id" gorm:"employee_id"`
}
