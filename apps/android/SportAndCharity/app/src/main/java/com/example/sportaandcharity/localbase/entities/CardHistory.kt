package com.example.sportaandcharity.localbase.entities

data class CardHistory(val id: Int,
                       val cardId:Int,
                       val typeAction: TypeAction,
                       val lat:Float,
                       val lon:Float,
                       val userId:Int,
                       val promotionId:Int,
)

enum class TypeAction{//alert -не похож или много проходит,block - явно он не тот
    INFO,ALERT,BLOCK
}

