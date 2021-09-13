package com.fatalzero.database.room

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.fatalzero.database.cursor.CarDatabaseCursor
import com.fatalzero.model.Car
import java.util.concurrent.Executors

private const val DATABASE_NAME ="car-database"
private const val SWITCH="switch"
private const val REPO_INIT_ERR_MESS="CarRepository must be initialized"

class CarRepository private constructor(val context: Context){
    private val database: CarDatabase =
        Room.databaseBuilder(
        context.applicationContext,
        CarDatabase::class.java,
        DATABASE_NAME
    ).build()


    private var carDao:CarDao=database.carDao()
      get(){
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            val dataSource = sharedPreferences.getBoolean(SWITCH,true)
             return if(dataSource) database.carDao() else
                 CarDatabaseCursor(context)
        }


    private val executor = Executors.newSingleThreadExecutor()

    fun getCars(order:String): LiveData<List<Car>> = carDao.getCarsOrderBy(order)

    fun getCar(id:Int):LiveData<Car?> = carDao.getCar(id)

    fun updateCar(car:Car){
           executor.execute{
              carDao.updateCar(car)
          }
        }

    fun deleteCar(car:Car){

           executor.execute{
              carDao.deleteCar(car)
          }
        }



    fun addCar(car:Car){
        executor.execute{
            carDao.addCar(car)
    }

    }

    companion object{
        private  var INSTANCE: CarRepository? = null

        fun initialize(context:Context){
            if(INSTANCE == null){
                INSTANCE = CarRepository(context)
            }
        }
        fun get(): CarRepository {
            return INSTANCE ?:
            throw IllegalStateException(REPO_INIT_ERR_MESS)
        }
    }
}