package model

type Path struct {
	ID       uint64   `json:"id" gorm:"primarykey,autoIncrement"`
	ActionID uint64   `json:"action_id"`
	Points   []Coords `json:"points" gorm:"foreignKey:PathId"`
}

type Coords struct {
	ID     uint64 `json:"id" gorm:"primarykey,autoIncrement"`
	PathId uint64 `json:"path_id"`
	Lat    int64  `json:"lat"`
	Lon    int64  `json:"lon"`
}
