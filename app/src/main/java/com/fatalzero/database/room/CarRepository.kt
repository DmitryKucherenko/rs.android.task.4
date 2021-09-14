package com.fatalzero.database.room

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.fatalzero.database.DaoFactory
import com.fatalzero.database.cursor.CarDatabaseCursor
import com.fatalzero.model.Car
import java.util.concurrent.Executors

private const val DATABASE_NAME = "car-database"
private const val SWITCH = "switch"
private const val REPO_INIT_ERR_MESS = "CarRepository must be initialized"

class CarRepository private constructor(daoFactory: DaoFactory) {


    private var carDao: CarDao = daoFactory.carDao

    fun getCars(order: String): LiveData<List<Car>> = carDao.getCarsOrderBy(order)

     fun getCar(id: Int): LiveData<Car?> = carDao.getCar(id)

    suspend fun updateCar(car: Car) {
        carDao.updateCar(car)
    }

    suspend fun deleteCar(car: Car) {
        carDao.deleteCar(car)
    }


    suspend fun addCar(car: Car) {
        carDao.addCar(car)
    }

    companion object {
        private var INSTANCE: CarRepository? = null

        fun initialize( daoFactory: DaoFactory) {
            if (INSTANCE == null) {
                INSTANCE = CarRepository(daoFactory)
            }
        }

        fun get(): CarRepository {
            return INSTANCE ?: throw IllegalStateException(REPO_INIT_ERR_MESS)
        }
    }
}