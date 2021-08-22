package com.fatalzero.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.fatalzero.model.Car

@Dao
interface CarDao{

    @Query("SELECT * FROM car")
    fun getCars(): LiveData<List<Car>>

    @Query("SELECT * FROM car WHERE id=(:id)")
    fun getCar(id:Int):LiveData<Car?>

    @Insert
    fun addCrime(car:Car)

    @Update
    fun updateCar(car:Car)

}