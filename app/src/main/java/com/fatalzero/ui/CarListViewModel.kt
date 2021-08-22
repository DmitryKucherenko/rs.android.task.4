package com.fatalzero.ui

import androidx.lifecycle.ViewModel
import com.fatalzero.database.CarRepository
import com.fatalzero.model.Car

class CarListViewModel: ViewModel() {
    private val carRepository = CarRepository.get()
    val carListLiveDate = carRepository.getCars()
//    val carListLiveDate = listOf<Car>(Car(1,"2","3",1))
    fun addCar(car: Car){
        carRepository.addCar(car)
    }
}