package com.example.sportandcharityadmin.ui.dashboard.rateRV

import androidx.recyclerview.widget.RecyclerView
import com.example.sportandcharityadmin.databinding.ItemRatingBinding

class RatingProjectsViewHolder(val binding: ItemRatingBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: Rating) {
        with(binding) {
           textViewPoints.text=item.numberOfPoints.toString()
            textViewPlace.text="Место "+item.place.toString()
            textViewName.text=item.name
        }
    }
}