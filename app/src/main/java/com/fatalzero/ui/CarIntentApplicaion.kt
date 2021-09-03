package com.fatalzero.ui

import android.app.Application
import com.fatalzero.database.room.CarRepository

class CarIntentApplicaion: Application() {
    override fun onCreate() {
        super.onCreate()
        CarRepository.initialize(this)
    }
}