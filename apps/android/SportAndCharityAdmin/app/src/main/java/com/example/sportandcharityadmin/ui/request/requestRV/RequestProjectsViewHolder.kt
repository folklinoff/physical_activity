package com.example.sportandcharityadmin.ui.request.requestRV

import androidx.recyclerview.widget.RecyclerView
import com.example.sportandcharityadmin.databinding.ItemRequestBinding
import com.example.sportandcharityadmin.ui.request.Request

class RequestProjectsViewHolder(val binding: ItemRequestBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: Request) {
        with(binding) {
          textViewName.text=item.nameActivity
            textViewPoints.text=item.time.toString()


        }
    }
}