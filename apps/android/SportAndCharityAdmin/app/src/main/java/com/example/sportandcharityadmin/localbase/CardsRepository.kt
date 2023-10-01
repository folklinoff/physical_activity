package com.example.sportandcharityadmin.localbase

import com.example.sportandcharityadmin.localbase.entities.Card

class CardsRepository(val dao: MainDao) {

     fun getCardByTagId(tagId: String): Card? {
        return fromCardDataItemToCard(dao.getCardByTagId(tagId))
    }

}