package model

type Action struct {
	ID               string   `gorm:"id,primarykey,autoIncrement"`
	Path             Path     `json:"path" gorm:"foreignKey:ActionID"`
	ActivityType     string   `json:"activity_type"`
	ActivityTypeId   uint64   `json:"activity_type_id"`
	EmployeeID       uint64   `json:"employee_id"`
	ImageDownloadURL string   `json:"image_download_url"`
	FundID           *int64   `json:"fund_id"`
	GoalId           *int64   `json:"goal_id"`
	ActionBody       Activity `json:"actionBody" gorm:"-"`
}
