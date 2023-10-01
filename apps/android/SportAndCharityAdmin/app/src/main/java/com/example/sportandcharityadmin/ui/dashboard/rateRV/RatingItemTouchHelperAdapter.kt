package com.example.sportandcharityadmin.ui.dashboard.rateRV

interface RatingItemTouchHelperAdapter {

    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean

    fun onItemDismiss(position: Int)

}