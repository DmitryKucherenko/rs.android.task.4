package com.fatalzero.adapter

import androidx.recyclerview.widget.DiffUtil
import com.fatalzero.model.Car

object DiffCallback: DiffUtil.ItemCallback<Car>() {
    override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean {
        return oldItem==newItem
    }

}