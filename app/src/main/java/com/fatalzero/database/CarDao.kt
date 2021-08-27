package com.fatalzero.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.fatalzero.model.Car


@Dao
interface CarDao{

//    @Query("SELECT * FROM Car ORDER BY :ord")
//    fun getCars(ord:String): LiveData<List<Car>>

    @RawQuery(observedEntities = [Car::class])
    fun getCars( query:SupportSQLiteQuery): LiveData<List<Car>>

    fun getCarsOrderBy(ord:String): LiveData<List<Car>>{
        val statement = "SELECT * FROM Car ORDER BY $ord"
        val query: SupportSQLiteQuery = SimpleSQLiteQuery(statement, arrayOf())
        return getCars(query)
    }


    @Query("SELECT * FROM Car WHERE id=(:id)")
    fun getCar(id:Int):LiveData<Car?>

    @Insert
    fun addCar(car:Car)

    @Update
    fun updateCar(car:Car)

    @Delete
    fun deleteCar(car:Car)

}