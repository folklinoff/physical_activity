package com.example.sportandcharityadmin.localbase.entities

data class CardHistory(val id: Int,
                       val cardId:Int,
                       val typeAction: TypeAction,
                       val lat:Float,
                       val lon:Float,
                       val userId:Int,
                       val promotionId:Int,
)

enum class TypeAction{
}

