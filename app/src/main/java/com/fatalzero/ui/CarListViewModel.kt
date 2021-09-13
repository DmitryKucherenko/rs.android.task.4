package com.fatalzero.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.fatalzero.database.room.CarRepository
import com.fatalzero.model.Car

private const val BRAND_SORT = "brand"

class CarListViewModel : ViewModel() {
    private var sort: MutableLiveData<String> = MutableLiveData(BRAND_SORT)

    private val carRepository = CarRepository.get()
    var carListLiveData: LiveData<List<Car>> = Transformations.switchMap(sort) { order ->
        carRepository.getCars(order)
    }

    fun sortBy(order: String) {
        sort.value = order
    }
}