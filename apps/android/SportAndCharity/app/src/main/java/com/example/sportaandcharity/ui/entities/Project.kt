package com.example.sportaandcharity.ui.entities

import android.media.Image
import java.util.Date

data class Project (
    val id: String,
    val name:String,
    val date: Date?,
    var isCompleted: Boolean,
    var image: Int,
    var description:String
)