package com.example.sportaandcharity.ui.projectRV

import androidx.recyclerview.widget.RecyclerView
import com.example.sportaandcharity.databinding.ItemProjectBinding
import com.example.sportaandcharity.ui.entities.Project

import java.text.DateFormat
import java.util.Date

class ProjectsViewHolder(val binding: ItemProjectBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: Project) {
        with(binding) {
            textViewName.text = item.name
            textViewDate.text = item.description
                // imageButtonDelete.setImageResource(item.image)

        }
    }
}