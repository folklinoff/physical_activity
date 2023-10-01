package com.example.sportaandcharity.ui.fond.fondRV

import androidx.recyclerview.widget.DiffUtil
import com.example.sportaandcharity.ui.entities.Fond
import com.example.sportaandcharity.ui.entities.Project


class FondDiffCallback : DiffUtil.ItemCallback<Fond>() {

    override fun areItemsTheSame(oldItem: Fond, newItem: Fond) =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: Fond, newItem: Fond) =
        oldItem == newItem
}