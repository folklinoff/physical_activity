package com.example.sportandcharityadmin.ui.notifications.projectRV

import androidx.recyclerview.widget.DiffUtil



class ProjectsDiffCallback : DiffUtil.ItemCallback<Project>() {

    override fun areItemsTheSame(oldItem: Project, newItem: Project) =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: Project, newItem: Project) =
        oldItem == newItem
}