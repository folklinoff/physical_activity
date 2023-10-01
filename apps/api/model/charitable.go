package model

type Charitable struct {
	ID uint64 `gorm:"charitable_id,primarykey,autoIncrement" json:"id"`
}
