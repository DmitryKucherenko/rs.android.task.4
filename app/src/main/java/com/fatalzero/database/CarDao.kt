package com.fatalzero.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.fatalzero.model.Car

@Dao
interface CarDao{

    @Query("SELECT * FROM Car")
    fun getCars(): LiveData<List<Car>>

    @Query("SELECT * FROM Car WHERE id=(:id)")
    fun getCar(id:Int):LiveData<Car?>

    @Insert
    fun addCar(car:Car)

    @Update
    fun updateCar(car:Car)

}