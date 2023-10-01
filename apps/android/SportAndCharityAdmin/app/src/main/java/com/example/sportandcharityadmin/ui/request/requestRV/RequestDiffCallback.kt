package com.example.sportandcharityadmin.ui.request.requestRV

import androidx.recyclerview.widget.DiffUtil
import com.example.sportandcharityadmin.ui.request.Request


class RequestDiffCallback : DiffUtil.ItemCallback<Request>() {

    override fun areItemsTheSame(oldItem: Request, newItem: Request) =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: Request, newItem: Request) =
        oldItem == newItem
}