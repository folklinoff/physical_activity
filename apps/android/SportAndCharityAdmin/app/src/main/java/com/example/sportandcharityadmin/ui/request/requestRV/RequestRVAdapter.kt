package com.example.sportandcharityadmin.ui.request.requestRV

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.example.sportandcharityadmin.databinding.ItemRequestBinding
import com.example.sportandcharityadmin.ui.request.Request


import java.util.Collections


class RequestRVAdapter() : ListAdapter<Request, RequestProjectsViewHolder>(RequestDiffCallback()),
    RequestItemTouchHelperAdapter {

    var onProjectSwipeListener: ((Request) -> Unit)? = null
    lateinit var projects: MutableList<Request>
    lateinit var itemRequestBinding: ItemRequestBinding

    fun submit(list: List<Request>, rv: RecyclerView) {
        projects = list.toMutableList()
        submitList(list) {
            rv.invalidateItemDecorations()
        }//иначе добавление нового элемента - проблема
    }

    override fun onItemDismiss(position: Int) {
        projects.removeAt(position)
        onProjectSwipeListener?.invoke(projects[position])
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestProjectsViewHolder {
        itemRequestBinding =
            ItemRequestBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RequestProjectsViewHolder(itemRequestBinding)
    }


    override fun onBindViewHolder(holder: RequestProjectsViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item)
        with(holder.binding) {
            buttonAccept.setOnClickListener {
                onItemDismiss(position)
            }
            buttonDecline.setOnClickListener {
                onItemDismiss(position)
            }
            buttonDefault.setOnClickListener {
                onItemDismiss(position)
            }
        }
    }

}