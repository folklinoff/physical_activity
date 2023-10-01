package com.example.sportandcharityadmin.ui.dashboard.rateRV

import androidx.recyclerview.widget.DiffUtil



class RatingDiffCallback : DiffUtil.ItemCallback<Rating>() {

    override fun areItemsTheSame(oldItem: Rating, newItem: Rating) =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: Rating, newItem: Rating) =
        oldItem == newItem
}