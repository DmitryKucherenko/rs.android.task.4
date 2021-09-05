package com.fatalzero.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.fatalzero.database.room.CarRepository
import com.fatalzero.model.Car

class AddCarViewModel: ViewModel() {
    private val carRepository = CarRepository.get()

    private val carIdLiveData = MutableLiveData<Int>()
    var carLiveDate: LiveData<Car?> =
        Transformations.switchMap(carIdLiveData){ carId->
            carRepository.getCar(carId)
        }


    fun addCar(car: Car){
        carRepository.addCar(car)
    }

    fun loadCar(carId:Int){
        carIdLiveData.value = carId
    }

    fun update(car:Car){
        carRepository.updateCar(car)
    }

    fun delete(car:Car){
        carRepository.deleteCar(car)
    }

}


