package com.fatalzero.ui

import android.app.Application
import com.fatalzero.database.DaoFactory
import com.fatalzero.database.room.CarRepository

class CarApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        CarRepository.initialize(DaoFactory(this))
    }
}