package com.example.sportandcharityadmin.ui.notifications.projectRV

import androidx.recyclerview.widget.RecyclerView

import com.example.sportandcharityadmin.databinding.ItemProjectBinding


class ProjectsViewHolder(val binding: ItemProjectBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: Project) {
        with(binding) {
           textViewEvent.text = item.name
            textViewDesc.text = item.description


        }
    }
}