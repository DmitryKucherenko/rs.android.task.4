package com.fatalzero.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fatalzero.R

class MainActivity : AppCompatActivity(R.layout.activity_main), AddCarFragment.CallBack,
    CarListFragment.CallBack,SettingsFragment.CallBack,ItemSelected {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openListFragment()
    }

    override fun openListFragment(){
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

    override fun openListFragmentBackStack() {
        supportFragmentManager.popBackStack()


    }

    override fun openSettingsFragment(){
        val containerFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        val fragment = SettingsFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
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

    override fun onItemSelected(id: Int) {
        val containerFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        val fragment = AddCarFragment.newInstance(id)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}