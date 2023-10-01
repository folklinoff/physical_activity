package model

type ActivityType struct {
	ID      uint64   `gorm:"primarykey,autoIncrement" json:"id"`
	Name    string   `json:"name"`
	Actions []Action `json:"actions" gorm:"foreignKey:ActivityTypeId"`
	We      uint64   `json:"weight"`
	Ps      uint64   `json:"points"`
}
