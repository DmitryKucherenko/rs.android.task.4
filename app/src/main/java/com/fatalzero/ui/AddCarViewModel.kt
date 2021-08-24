package com.fatalzero.ui

import androidx.lifecycle.ViewModel
import com.fatalzero.database.CarRepository
import com.fatalzero.model.Car

class AddCarViewModel: ViewModel() {
    private val carRepository = CarRepository.get()
    var car:Car?=null

    fun addCar(car: Car){
        carRepository.addCar(car)
    }
}


