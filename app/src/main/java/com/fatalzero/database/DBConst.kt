package com.fatalzero.database

object  DBConst {
     const val DATABASE_NAME = "car-database"
     const val TABLE_NAME = "Car"
     const val DATABASE_VERSION = 1
     const val CREATE_TABLE_SQL =
        "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
                "id	INTEGER NOT NULL," +
                "brand	TEXT NOT NULL," +
                "model	TEXT NOT NULL," +
                "mileage	INTEGER NOT NULL," +
                "PRIMARY KEY(id AUTOINCREMENT)" +
                ");"

    const val BRAND_FIELD = "brand"
    const val MODEL_FIELD = "model"
    const val MILEAGE_FIELD = "mileage"
    const val ID = "id"

}