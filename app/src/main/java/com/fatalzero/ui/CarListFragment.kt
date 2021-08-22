package com.fatalzero.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fatalzero.adapter.CarAdapter
import com.fatalzero.databinding.FragmentCarBinding
import com.fatalzero.model.Car

class CarListFragment: Fragment() {

    private var _binding: FragmentCarBinding? = null
    private val binding get() = _binding!!
    private lateinit var carRecyclerView:RecyclerView
    private  var adapter: CarAdapter?=null



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding  = FragmentCarBinding.inflate(inflater,container,false)
        val view = binding.root
        carRecyclerView = binding.
        return view
    }





    companion object{
        fun newInstance():CarListFragment{
            return CarListFragment()
        }

    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}