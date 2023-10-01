package com.example.sportaandcharity.ui.fond.fondRV

import androidx.recyclerview.widget.RecyclerView
import com.example.sportaandcharity.databinding.ItemFondBinding
import com.example.sportaandcharity.ui.entities.Fond
import com.example.sportaandcharity.ui.entities.Project

class FondProjectsViewHolder(val binding: ItemFondBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: Fond) {
        with(binding) {
            tvName.text = item.name
            tvDescr.text = item.description
                 iv.setImageResource(item.image)

        }
    }
}