package com.example.sportandcharityadmin.ui.dashboard.rateRV

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sportandcharityadmin.databinding.ItemRatingBinding


import java.util.Collections


class RatingRVAdapter() : ListAdapter<Rating, RatingProjectsViewHolder>(RatingDiffCallback()),
    RatingItemTouchHelperAdapter {

    lateinit var projects: MutableList<Rating>
    lateinit var itemRatingBinding: ItemRatingBinding
    var onProjectClickListener: ((Rating) -> Unit)? = null
    fun submit(list:  List<Rating>, rv: RecyclerView) {
        projects=list.toMutableList()
        submitList(list){
            rv.invalidateItemDecorations()
        }//иначе добавление нового элемента - проблема
    }

    override fun onItemDismiss(position: Int) {
        projects.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(projects, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(projects, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingProjectsViewHolder {
        itemRatingBinding = ItemRatingBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RatingProjectsViewHolder(itemRatingBinding)
    }


    override fun onBindViewHolder(holder: RatingProjectsViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item)
        with(holder.binding) {
            button.setOnClickListener() {
                onProjectClickListener?.invoke(item)
            }


        }

    }

}