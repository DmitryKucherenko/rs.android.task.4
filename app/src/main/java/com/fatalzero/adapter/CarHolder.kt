package com.fatalzero.adapter

import android.content.res.Resources
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fatalzero.databinding.FragmentCarBinding
import com.fatalzero.model.Car
import com.fatalzero.ui.ItemClickListener

class CarHolder(
    private val itemClickListener:ItemClickListener,
    private val binding: FragmentCarBinding,
    private val resources: Resources

) : RecyclerView.ViewHolder(binding.root) {
    private var car: Car? = null
    private val brand: TextView = binding.brandTextView
    private val model: TextView = binding.modelTextView
    private val mileage: TextView = binding.mileageTextView


    fun bind(car: Car) {
        this.car = car
        brand.text = car.brand
        model.text = car.model
        mileage.text = car.mileage.toString()
        itemView.setOnClickListener{
            itemClickListener.onItemClick(car.id)
        }



    }

}