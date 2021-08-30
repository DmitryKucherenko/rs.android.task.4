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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fatalzero.databinding.FragmentAddCarBinding
import com.fatalzero.model.Car


class AddCarFragment : Fragment() {

    private var _binding: FragmentAddCarBinding? = null
    private val binding get() = _binding!!
    private var addButton: Button? = null
    private   var id:Int? = null
    private lateinit var  car:Car
    private var callBack: CallBack?=null

    interface CallBack{
        fun openListFragmentBackStack()

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
            car?.brand = sequence.toString()
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
            car?.model = sequence.toString()
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
            car?.mileage = sequence.toString().toIntOrNull()?:0
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
        val bundle = this.arguments
         id=bundle?.getInt("id")
        if(id!=null){
              addCarViewModel.loadCar(id!!)
        }else {
            this.car=Car()
            addCarViewModel.car = car
        }

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
            if(id==null)
            addCarViewModel.addCar(car) else
                addCarViewModel.update(car)
            callBack?.openListFragmentBackStack()
        }

        return binding.root
    }


    companion object {

        @JvmStatic
        fun newInstance(id:Int?=null):AddCarFragment {
            val addCarFragment = AddCarFragment()

         id?.let{
             val bundle = Bundle()
             bundle.putInt("id",it)
             addCarFragment.arguments=bundle
         }
return addCarFragment

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addCarViewModel.carLiveDate.observe(
            viewLifecycleOwner,
            Observer { car ->
                car?.let {
                    this.car = it
                    updateUI()
                }
            })
    }

    private fun updateUI() {
        binding.brandName.setText(car.brand)
        binding.modelName.setText(car.model)
        binding.mileage.setText(car.mileage.toString())
    }
}