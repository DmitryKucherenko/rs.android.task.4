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

private const val ID = "id"

class AddCarFragment : Fragment() {

    private var _binding: FragmentAddCarBinding? = null
    private val binding get() = _binding!!
    private var saveButton: Button? = null
    private var delButton: Button? = null
    private var id: Int? = null

    private val addCarViewModel: AddCarViewModel by lazy {

        ViewModelProvider(this).get(AddCarViewModel::class.java)
    }

    private var callBack: CallBack? = null

    interface CallBack {
        fun openListFragment()

    }

    private fun textWatcher(actionTextChange: (sequence: CharSequence?) -> Unit) =
        object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(
                sequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                actionTextChange(sequence)
            }

            override fun afterTextChanged(s: Editable?) {}
        }


    private val brandWatcher = textWatcher { addCarViewModel.car.brand = it.toString() }
    private val modelWatcher = textWatcher { addCarViewModel.car.model = it.toString() }
    private val mileageWatcher =
        textWatcher { addCarViewModel.car.mileage = it.toString().toIntOrNull() ?: 0 }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        callBack = context as CallBack
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = arguments?.getInt(ID)
        id?.let { addCarViewModel.loadCar(it) }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddCarBinding.inflate(inflater, container, false)
        saveButton = binding.saveButton
        delButton = binding.deleteButton
        if (id == null) delButton?.visibility = View.INVISIBLE
        else delButton?.visibility = View.VISIBLE
        binding.brandName.addTextChangedListener(brandWatcher)
        binding.modelName.addTextChangedListener(modelWatcher)
        binding.mileage.addTextChangedListener(mileageWatcher)
        saveButton?.setOnClickListener {
            if (id == null)
                addCarViewModel.addCar(addCarViewModel.car) else
                addCarViewModel.update(addCarViewModel.car)
            callBack?.openListFragment()
        }
        delButton?.setOnClickListener {
            addCarViewModel.delete(addCarViewModel.car)
            callBack?.openListFragment()
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addCarViewModel.carLiveDate.observe(
            viewLifecycleOwner,
            { car ->
                car?.let {
                    this.addCarViewModel.car = it
                    updateUI()
                }
            })
    }

    private fun updateUI() {
        binding.brandName.setText(addCarViewModel.car.brand)
        binding.modelName.setText(addCarViewModel.car.model)
        binding.mileage.setText(addCarViewModel.car.mileage.toString())
    }
}