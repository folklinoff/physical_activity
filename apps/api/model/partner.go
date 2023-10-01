package model

type Partner struct {
	ID    uint64 `gorm:"partner_id,primarykey,autoIncrement" json:"id"`
	Name  string `json:"name" gorm:"name"`
	Promo string `json:"promo" gorm:"promo"`
}
