package com.example.sportaandcharity.localbase.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "cardHistoryDataItem")
data class CardHistoryDataItem(
    @PrimaryKey val id: Int,
    val cardId:Int,
    val typeAction:Int,
    val lat:Float,
    val lon:Float,
    val userId:Int,
    val promotionId:Int)