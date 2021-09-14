package com.fatalzero.ui

import androidx.lifecycle.*
import com.fatalzero.database.room.CarRepository
import com.fatalzero.model.Car
import kotlinx.coroutines.launch

class AddCarViewModel : ViewModel() {
    private val carRepository = CarRepository.get()
    private val carIdLiveData = MutableLiveData<Int>()
    var car: Car = Car()
    var carLiveDate: LiveData<Car?> =
        Transformations.switchMap(carIdLiveData) { carId ->
            carRepository.getCar(carId)
        }

    fun addCar(car: Car) {
        viewModelScope.launch {
            carRepository.addCar(car)
        }
    }

    fun loadCar(carId: Int) {
        if(carId!=carIdLiveData.value)
        carIdLiveData.value = carId
    }

    fun update(car: Car) {
        viewModelScope.launch {
            carRepository.updateCar(car)
        }
    }

    fun delete(car: Car) {
        viewModelScope.launch {
            carRepository.deleteCar(car)
        }
    }



}


