package com.fatalzero.database.cursor

import android.content.ContentValues
import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fatalzero.database.room.CarDao
import com.fatalzero.model.Car


private const val LOG_TAG = "SQLiteOpenHelper"
private const val DATABASE_NAME = "car-database"
private const val TABLE_NAME = "Car"
private const val DATABASE_VERSION = 1
private const val CREATE_TABLE_SQL =
    "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "id	INTEGER NOT NULL," +
            "brand	TEXT NOT NULL," +
            "model	TEXT NOT NULL," +
            "mileage	INTEGER NOT NULL," +
            "PRIMARY KEY(id AUTOINCREMENT)" +
            ");"


class CarDatabaseCursor(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION), CarDao {
    override fun onCreate(db: SQLiteDatabase?) {
        try {
            db?.execSQL(CREATE_TABLE_SQL)

        } catch (exception: SQLException) {
            Log.e(LOG_TAG, "SQLiteOpenHelper", exception)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.d(LOG_TAG, "onUpgrade called")
    }




    override fun getCarsOrderBy(order: String): LiveData<List<Car>> {
        Log.d(LOG_TAG, "Cursor getCarsOrderBy($order)")
        val carListLiveData = MutableLiveData<List<Car>>()
        val listOfCars = mutableListOf<Car>()
        val db = writableDatabase
        val selectQuery = "SELECT * FROM $TABLE_NAME ORDER BY $order"
        val cursor = db.rawQuery(selectQuery,null)
        cursor?.let{
            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndex("id"))
                    val brand = cursor.getString(cursor.getColumnIndex("brand"))
                    val model = cursor.getString(cursor.getColumnIndex("model"))
                    val mileage = cursor.getInt(cursor.getColumnIndex("mileage"))
                    listOfCars.add(Car(id, brand, model, mileage))
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        carListLiveData.value = listOfCars
        return carListLiveData
    }

    override fun getCar(id: Int): LiveData<Car?> {
        Log.d(LOG_TAG, "getCar($id)")
        val carLiveData = MutableLiveData<Car>()
        val db = writableDatabase

        val selectQuery = "SELECT * FROM $TABLE_NAME WHERE id = $id"
        val cursor = db.rawQuery(selectQuery, null)
        Log.d(LOG_TAG, "$cursor")
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {

                    val id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")))
                    val brand = cursor.getString(cursor.getColumnIndex("brand"))
                    val model = cursor.getString(cursor.getColumnIndex("model"))
                    val mileage = cursor.getInt(cursor.getColumnIndex("mileage"))
                    Log.d(LOG_TAG, "FROM GET CAR CURSOR $id $brand $model $mileage")
                    val car =  Car(id, brand, model, mileage)
                    Log.d(LOG_TAG, "FROM GET CAR CURSOR $car")
                    carLiveData.value = car
                } while (cursor.moveToNext())
            }
        }
            cursor.close()
        Log.d(LOG_TAG, "FROM GET CAR CURSOR ${carLiveData.value}")
            return carLiveData
        }


    override fun addCar(car: Car) {
        Log.d(LOG_TAG, "Cursor addCar($car)")
        val db = writableDatabase
        val values = ContentValues()
//        values.put("id",car.id)
        values.put("brand",car.brand)
        values.put("model",car.model)
        values.put("mileage",car.mileage)
        db.insert(TABLE_NAME, null, values)
        

         //db.execSQL("INSERT INTO $TABLE_NAME VALUES ('${car.id}','${car.brand}','${car.model}','${car.mileage}');")


        db.close()
    }

    override fun updateCar(car: Car) {
        Log.d(LOG_TAG, "Cursor updateCar($car)")
        val db = writableDatabase
        val values = ContentValues()
        values.put("id",car.id)
        values.put("brand",car.brand)
        values.put("model",car.model)
        values.put("mileage",car.mileage)
        db.update(TABLE_NAME, values, "id" + "=?", arrayOf(car.id.toString()))
        db.close()
    }

    override fun deleteCar(car: Car) {
        Log.d(LOG_TAG, "Cursor deleteCar($car)")
        val db = writableDatabase
        db.delete(TABLE_NAME, "id" + "=?", arrayOf(car.id.toString()))
        db.close()
    }
}

