package com.fatalzero.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.fatalzero.R

private const val ID = "id"

class MainActivity : AppCompatActivity(R.layout.activity_main), AddCarFragment.CallBack,
    CarListFragment.CallBack,ItemClickListener {

    private var navController: NavController?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = this.findNavController(R.id.fragment_container)
    }




    override fun openListFragment() {
        navController?.popBackStack()

    }

    override fun openSettingsFragment(){
        navController?.navigate(R.id.action_carListFragment_to_settingsFragment)
    }



    override fun openAddFragment() {
        navController?.navigate(R.id.action_carListFragment_to_addCarFragment)
    }

    override fun onItemClick(id: Int) {
        navController?.navigate(R.id.action_carListFragment_to_addCarFragment,bundleOf(ID to id))
    }
}

