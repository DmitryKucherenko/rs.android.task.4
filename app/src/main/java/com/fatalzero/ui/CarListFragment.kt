package com.fatalzero.ui

import android.content.Context
import android.os.Bundle
import android.text.TextWatcher
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fatalzero.R
import com.fatalzero.adapter.CarAdapter
import com.fatalzero.databinding.FragmentCarBinding
import com.fatalzero.databinding.FragmentListBinding
import com.fatalzero.model.Car
import com.google.android.material.floatingactionbutton.FloatingActionButton


class CarListFragment: Fragment() {



    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var carRecyclerView:RecyclerView
    private  var adapter: CarAdapter?= CarAdapter()
    private var button:FloatingActionButton?=null
    private val carListViewModel: CarListViewModel by lazy{
        ViewModelProvider(this).get(CarListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callBack=context as CallBack
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.sort_menu,menu)
    }

    interface CallBack{
        fun openAddFragment(){}
    }

    private var callBack: CallBack?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        carListViewModel.carListLiveDate.observe(viewLifecycleOwner,
            {cars ->
                cars?.let{
                    updateUI(it)
                }

            })

    }

    private fun updateUI(cars: List<Car>) {
        println(cars.size)
        adapter?.submitList(cars)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding  = FragmentListBinding.inflate(inflater,container,false)
        val view = binding.root
        button = binding.floatingActionButton
        carRecyclerView = binding.carRecyclerView
        carRecyclerView.layoutManager = LinearLayoutManager(context)
        carRecyclerView.adapter=adapter
        button?.setOnClickListener {
            callBack?.openAddFragment()
        }
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