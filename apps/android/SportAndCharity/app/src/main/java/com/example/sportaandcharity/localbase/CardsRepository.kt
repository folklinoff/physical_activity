package com.example.sportaandcharity.localbase

import com.example.sportaandcharity.localbase.entities.Card

class CardsRepository(val dao: MainDao) {

     fun getCardByTagId(tagId: String): Card? {
        return fromCardDataItemToCard(dao.getCardByTagId(tagId))
    }

}