package com.fatalzero.database.cursor

import android.content.ContentValues
import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.fatalzero.database.DBConst
import com.fatalzero.database.room.CarDao
import com.fatalzero.model.Car
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


private const val LOG_TAG = "SQLiteOpenHelper"
private const val SQLITE_ERR = "SQLiteOpenHelper ERROR:"


class CarDatabaseCursor(context: Context) :
    SQLiteOpenHelper(context, DBConst.DATABASE_NAME, null, DBConst.DATABASE_VERSION), CarDao {
    override fun onCreate(db: SQLiteDatabase?) {
        try {
            db?.execSQL(DBConst.CREATE_TABLE_SQL)
        } catch (exception: SQLException) {
            Log.e(LOG_TAG, SQLITE_ERR, exception)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }


    private suspend fun getCarsList(orderList: String): List<Car> {
        return withContext(Dispatchers.IO) {
            val listOfCars = mutableListOf<Car>()
            val db = writableDatabase
            val selectQuery = "SELECT * FROM ${DBConst.TABLE_NAME} ORDER BY $orderList"
            val cursor = db.rawQuery(selectQuery, null)
            cursor?.let {
                if (cursor.moveToFirst()) {
                    do {
                        val id = cursor.getInt(cursor.getColumnIndex(DBConst.ID))
                        val brand = cursor.getString(cursor.getColumnIndex(DBConst.BRAND_FIELD))
                        val model = cursor.getString(cursor.getColumnIndex(DBConst.MODEL_FIELD))
                        val mileage = cursor.getInt(cursor.getColumnIndex(DBConst.MILEAGE_FIELD))
                        listOfCars.add(Car(id, brand, model, mileage))
                    } while (cursor.moveToNext())
                }
            }
            cursor.close()
            listOfCars
        }
    }

    override fun getCarsOrderBy(order: String): LiveData<List<Car>> {
        return liveData<List<Car>> {
            emit(getCarsList(order))
        }
    }


    override fun getCar(id: Int): LiveData<Car?> {
        val carLiveData = MutableLiveData<Car>()
        val db = writableDatabase
        val selectQuery = "SELECT * FROM ${DBConst.TABLE_NAME} WHERE ${DBConst.ID} = $id"

        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val brand = cursor.getString(cursor.getColumnIndex(DBConst.BRAND_FIELD))
                    val model = cursor.getString(cursor.getColumnIndex(DBConst.MODEL_FIELD))
                    val mileage = cursor.getInt(cursor.getColumnIndex(DBConst.MILEAGE_FIELD))
                    val car = Car(id, brand, model, mileage)
                    carLiveData.value = car
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        return carLiveData
    }


    override suspend fun addCar(car: Car) {
        println("addCar Cursor")
        val db = writableDatabase
        val values = ContentValues()
        values.put(DBConst.BRAND_FIELD, car.brand)
        values.put(DBConst.MODEL_FIELD, car.model)
        values.put(DBConst.MILEAGE_FIELD, car.mileage)
        db.insert(DBConst.TABLE_NAME, null, values)
        db.close()
    }

    override suspend fun updateCar(car: Car) {
        println("update Cursor")
        val db = writableDatabase
        val values = ContentValues()
        values.put(DBConst.ID, car.id)
        values.put(DBConst.BRAND_FIELD, car.brand)
        values.put(DBConst.MODEL_FIELD, car.model)
        values.put(DBConst.MILEAGE_FIELD, car.mileage)
        db.update(DBConst.TABLE_NAME, values, "${DBConst.ID}=?", arrayOf(car.id.toString()))
        db.close()
    }

    override suspend fun deleteCar(car: Car) {
        val db = writableDatabase
        db.delete(DBConst.TABLE_NAME, "${DBConst.ID}=?", arrayOf(car.id.toString()))
        db.close()
    }
}

