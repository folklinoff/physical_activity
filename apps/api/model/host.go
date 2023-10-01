package model

type Host struct {
	ID uint64 `gorm:"host_id,primarykey,autoIncrement" json:"id"`

	Name string `json:"name"`
}
