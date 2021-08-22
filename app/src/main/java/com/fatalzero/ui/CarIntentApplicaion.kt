package com.fatalzero.ui

import android.app.Application
import com.fatalzero.database.CarRepository

class CarIntentApplicaion: Application() {
    override fun onCreate() {
        super.onCreate()
        CarRepository.initialize(this)
    }
}