package com.fatalzero.database

import android.content.Context
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.fatalzero.database.cursor.CarDatabaseCursor
import com.fatalzero.database.room.CarDao
import com.fatalzero.database.room.CarDatabase


private const val SWITCH = "switch"

class DaoFactory(val context: Context) {

    private val sharedPreferences by lazy { PreferenceManager.getDefaultSharedPreferences(context) }
    private val database: CarDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            CarDatabase::class.java,
            DBConst.DATABASE_NAME
        ).build()

    private val RoomImpl by lazy { database.carDao() }
    private val CursorImpl by lazy { CarDatabaseCursor(context) }

    var carDao: CarDao = database.carDao()
        get() = if (sharedPreferences.getBoolean(SWITCH, true)) RoomImpl else CursorImpl

}