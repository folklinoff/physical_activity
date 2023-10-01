package model

type Employee struct {
	ID          uint64 `gorm:"employee_id,primarykey,autoIncrement" json:"id"`
	PhoneNumber string `json:"phone_number"`
	Password    string `json:"password"`
	Name        string `gorm:"name" json:"name"`
	Surname     string `gorm:"surname" json:"surname"`
	Patronymic  string `gorm:"patronymic" json:"patronymic"`
	Position    string `gorm:"position" json:"position"`
	Department  string `gorm:"department" json:"department"`
	Balance     int64  `gorm:"balance" json:"balance"`
	TotalPoints int64  `gorm:"total_points" json:"total_points"`
	Weight      int64  `gorm:"weight" json:"weight"`
	Height      int64  `json:"height"`

	Fund   *Fund  `gorm:"foreignKey:FundId" json:"-"`
	Goal   *Goal  `gorm:"foreignKey:GoalId" json:"-"`
	FundId *int64 `json:"fund_id"`
	GoalId *int64 `json:"goal_id"`

	Actions []Action `gorm:"foreignKey:EmployeeID" json:"action"`
	Events  []Event  `gorm:"many2many,foreignKey:EmployeeId" json:"events"`
}
