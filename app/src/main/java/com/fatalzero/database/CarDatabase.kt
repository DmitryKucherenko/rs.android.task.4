package com.fatalzero.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fatalzero.model.Car


@Database(entities=[Car::class],version = 1, exportSchema = false)
abstract class CarDatabase: RoomDatabase() {
    abstract fun carDao():CarDao
}