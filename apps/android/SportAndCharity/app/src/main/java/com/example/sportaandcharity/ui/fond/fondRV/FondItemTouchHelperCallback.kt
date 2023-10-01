package com.example.sportaandcharity.ui.fond.fondRV

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

import com.example.sportaandcharity.ui.fond.fondRV.FondItemTouchHelperAdapter


class FondItemTouchHelperCallback: ItemTouchHelper.Callback/*, Swipable*/ {

    //override var onProjectSwipeListener: ((Int) -> Unit)?=null

    private var mAdapter: FondItemTouchHelperAdapter? = null

    constructor(adapter: FondItemTouchHelperAdapter?) {
        mAdapter = adapter
    }
    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: ViewHolder): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: ViewHolder,
        target: ViewHolder
    ): Boolean {
        mAdapter!!.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }



    override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
        mAdapter!!.onItemDismiss(viewHolder.adapterPosition)
       // Log.d("SwipableRight", "onViewCreated: ")
       // onProjectSwipeListener?.invoke(viewHolder.adapterPosition)
    }


}