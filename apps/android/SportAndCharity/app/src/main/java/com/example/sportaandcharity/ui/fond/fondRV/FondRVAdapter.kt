package com.example.sportaandcharity.ui.fond.fondRV

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sportaandcharity.databinding.ItemFondBinding
import com.example.sportaandcharity.databinding.ItemProjectBinding
import com.example.sportaandcharity.ui.entities.Fond
import com.example.sportaandcharity.ui.entities.Project


import java.util.Collections


class FondRVAdapter() : ListAdapter<Fond, FondProjectsViewHolder>(FondDiffCallback()),
    FondItemTouchHelperAdapter {

    var onProjectClickListener: ((Fond) -> Unit)? = null
    lateinit var projects: MutableList<Fond>
    lateinit var itemFondBinding: ItemFondBinding

    fun submit(list:  List<Fond>, rv: RecyclerView) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FondProjectsViewHolder {
        itemFondBinding = ItemFondBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return FondProjectsViewHolder(itemFondBinding)
    }


    override fun onBindViewHolder(holder: FondProjectsViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item)


    }

}