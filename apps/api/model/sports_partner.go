package model

type SportsPartner struct {
	ID uint64 `gorm:"sports_partner_id,primarykey,autoIncrement" json:"id"`

	Name string `json:"name"`
}
