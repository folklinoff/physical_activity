package com.example.sportandcharityadmin.ui.request.requestRV

interface RequestItemTouchHelperAdapter {

    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean

    fun onItemDismiss(position: Int)

}