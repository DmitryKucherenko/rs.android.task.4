package com.fatalzero.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.fatalzero.database.CarRepository
import com.fatalzero.model.Car

class CarListViewModel: ViewModel() {
    private var sort:MutableLiveData<String> = MutableLiveData("brand")

    //set(value) = this.value=value
    private val carRepository = CarRepository.get()
   // val carListLiveDate = carRepository.getCars(order)
    var carListLiveData: LiveData<List<Car>> = Transformations.switchMap(sort){ order -> carRepository.getCars(order)}

    fun sortBy(order:String){
        sort.value=order
    }
}