package com.fatalzero.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fatalzero.R
import com.fatalzero.model.Car

class MainActivity : AppCompatActivity(R.layout.activity_main),CarFragment.CallBack {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val containerFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (containerFragment == null) {
            val fragment = CarListFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }

    override fun addCar(car: Car) {
        val fragment = CarFragment.newInstance("","")
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .addToBackStack(null)
            .commit()
    }
}