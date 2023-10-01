package com.example.sportaandcharity.ui.projectRV

import androidx.recyclerview.widget.DiffUtil
import com.example.sportaandcharity.ui.entities.Project


class ProjectsDiffCallback : DiffUtil.ItemCallback<Project>() {

    override fun areItemsTheSame(oldItem: Project, newItem: Project) =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: Project, newItem: Project) =
        oldItem == newItem
}