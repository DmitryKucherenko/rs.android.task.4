package com.fatalzero.adapter

import android.content.res.Resources
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fatalzero.databinding.FragmentCarBinding
import com.fatalzero.model.Car

class CarHolder(
    private val binding: FragmentCarBinding,
    private val resources: Resources

) : RecyclerView.ViewHolder(binding.root){
    private var car: Car?=null
    private val brand: TextView =binding.brandTextView
    private val model: TextView =binding.modelTextView
    private val mileage: TextView =binding.mileageTextView

    fun bind(car: Car){
        this.car = car
        brand.text=car.brand
        model.text=car.model
        mileage.text = car.mileage.toString()
    }

}