package com.example.sportaandcharity.localbase.entities

import java.util.Date

data class WorkShift(val id:Int,
                     val startDate: Date,
                     var endDate:Date?
                     )