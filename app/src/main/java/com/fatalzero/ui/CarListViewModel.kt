package com.fatalzero.ui

import androidx.lifecycle.ViewModel
import com.fatalzero.database.CarRepository
import com.fatalzero.model.Car

class CarListViewModel: ViewModel() {
    private val carRepository = CarRepository.get()
    val carListLiveDate = carRepository.getCars()
}