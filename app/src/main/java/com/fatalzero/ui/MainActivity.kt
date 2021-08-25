package com.fatalzero.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.fatalzero.R
import com.fatalzero.model.Car

class MainActivity : AppCompatActivity(R.layout.activity_main), AddCarFragment.CallBack,
    CarListFragment.CallBack {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val containerFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (containerFragment == null) {
            val fragment = CarListFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun openListFragment() {
        supportFragmentManager.popBackStack()


    }

    override fun openAddFragment() {


        val containerFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        val fragment = AddCarFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()

    }
}