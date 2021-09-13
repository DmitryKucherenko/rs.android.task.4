package com.fatalzero.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fatalzero.R
import com.fatalzero.adapter.CarAdapter
import com.fatalzero.databinding.FragmentListBinding
import com.fatalzero.model.Car
import com.google.android.material.floatingactionbutton.FloatingActionButton


private const val SORT_BY = "sort_by"
private const val BRAND_FIELD = "brand"

class CarListFragment : Fragment() {

    private val TAG = "myLogs"

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var carRecyclerView: RecyclerView
    private var adapter: CarAdapter? = null
    private var button: FloatingActionButton? = null
    private val carListViewModel: CarListViewModel by activityViewModels()


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.sort_setting -> {
                callBack?.openSettingsFragment()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        callBack = context as CallBack
        itemClickListener = context as ItemClickListener
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.sort_menu, menu)
    }

    interface CallBack {
        fun openAddFragment()
        fun openSettingsFragment()
    }

    private var callBack: CallBack? = null
    private var itemClickListener: ItemClickListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val order = sharedPreferences.getString(SORT_BY, BRAND_FIELD) ?: BRAND_FIELD


        carListViewModel.sortBy(order)
        carListViewModel.carListLiveData.observe(viewLifecycleOwner,
            { cars ->
                cars?.let {
                    updateUI(it)
                }
            })


    }

    private fun updateUI(cars: List<Car>) {
        Log.d(TAG, "${cars.size}")
        adapter?.submitList(cars)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root
        button = binding.floatingActionButton
        carRecyclerView = binding.carRecyclerView
        carRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = itemClickListener?.let { CarAdapter(it) }
        carRecyclerView.adapter = adapter
        button?.setOnClickListener {
            callBack?.openAddFragment()
        }
        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}