package com.fatalzero.database.room

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.fatalzero.model.Car


@Dao
interface CarDao{


//    @RawQuery(observedEntities = [Car::class])
//    fun getCars( query:SupportSQLiteQuery): LiveData<List<Car>>

//    fun getCarsOrderBy(order:String): LiveData<List<Car>>{
//        val statement = "SELECT * FROM Car ORDER BY $order"
//
//        val query: SupportSQLiteQuery = SimpleSQLiteQuery(statement, arrayOf())
//        return getCars(query)
//    }
    @Query("SELECT * FROM Car ORDER BY " +
            "CASE WHEN :order = 'brand' THEN brand END,"+
            "CASE WHEN :order = 'model' THEN model END,"+
            "CASE WHEN :order = 'mileage' THEN mileage END")
    fun getCarsOrderBy(order:String): LiveData<List<Car>>



    @Query("SELECT * FROM Car WHERE id=(:id)")
    fun getCar(id:Int):LiveData<Car?>

    @Insert
    fun addCar(car:Car)

    @Update
    fun updateCar(car:Car)

    @Delete
    fun deleteCar(car:Car)

}