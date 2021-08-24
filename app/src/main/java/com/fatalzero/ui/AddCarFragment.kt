package com.fatalzero.ui

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.fatalzero.databinding.FragmentAddCarBinding
import com.fatalzero.model.Car


class AddCarFragment : Fragment() {

    private var _binding: FragmentAddCarBinding? = null
    private val binding get() = _binding!!
    private var addButton: Button? = null
private lateinit var car:Car
    private var callBack: CallBack?=null

    interface CallBack{
        fun openListFragment(){}
    }

    val brandWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }


        override fun onTextChanged(
            sequence: CharSequence?,
            start: Int,
            before: Int,
            count: Int
        ) {
            addCarViewModel.car?.brand = sequence.toString()
        }

        override fun afterTextChanged(s: Editable?) {
        }


    }

    val modelWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }


        override fun onTextChanged(
            sequence: CharSequence?,
            start: Int,
            before: Int,
            count: Int
        ) {
            addCarViewModel.car?.model = sequence.toString()
        }

        override fun afterTextChanged(s: Editable?) {
        }


    }


    val miliageWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }


        override fun onTextChanged(
            sequence: CharSequence?,
            start: Int,
            before: Int,
            count: Int
        ) {
            addCarViewModel.car?.mileage = sequence.toString()
        }

        override fun afterTextChanged(s: Editable?) {
        }


    }

    private val addCarViewModel: AddCarViewModel by lazy{
        ViewModelProvider(this).get(AddCarViewModel::class.java)
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        callBack=context as CallBack

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        car = Car()
        addCarViewModel.car=car

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddCarBinding.inflate(inflater, container, false)
        addButton=binding.button
        binding.brandName.addTextChangedListener(brandWatcher)
        binding.modelName.addTextChangedListener(modelWatcher)
        binding.mileage.addTextChangedListener(miliageWatcher)
        addButton?.setOnClickListener{
            addCarViewModel.addCar(car)
            callBack?.openListFragment()
        }
        return binding.root
    }


    companion object {

        @JvmStatic
        fun newInstance() =
            AddCarFragment()
    }
}