package com.fatalzero.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Car(
    @PrimaryKey(autoGenerate = true) val id:Int=0,
    var brand:String="", var model:String="", var mileage: Int =0)