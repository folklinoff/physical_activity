package com.example.sportaandcharity.ui.fond.fondRV

interface FondItemTouchHelperAdapter {

    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean

    fun onItemDismiss(position: Int)

}