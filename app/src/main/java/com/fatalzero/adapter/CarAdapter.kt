package com.fatalzero.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.fatalzero.model.Car
import com.fatalzero.databinding.FragmentCarBinding

private class CarAdapter() : ListAdapter<Car, CarHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FragmentCarBinding.inflate(layoutInflater, parent, false)
        return CarHolder(binding,binding.root.context.resources)
    }

    override fun onBindViewHolder(holder: CarHolder, position: Int) {
        val car = currentList[position]
        holder.bind(car)
    }

    override fun getItemCount()=currentList.size
    }