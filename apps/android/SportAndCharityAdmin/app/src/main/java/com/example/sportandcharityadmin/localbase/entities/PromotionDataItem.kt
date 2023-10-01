package com.example.sportandcharityadmin.localbase.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "promotions")
data class PromotionDataItem(
    @PrimaryKey val id: Int,
    val name: String,
    val promotion:Int,
    @Embedded
    val promotionConditions: Conditions,
)

class Conditions(val dateOfBirth: Long,
                 val conditionDateOfBirth:String,///< > = <= >=
                 val gender:Boolean,
                 val conditionGender:String,// MALE FEMAlE
                 val city:String,
                 val conditionCity:String){


}