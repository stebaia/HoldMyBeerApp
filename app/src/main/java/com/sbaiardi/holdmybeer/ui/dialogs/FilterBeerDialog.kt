package com.sbaiardi.holdmybeer.ui.dialogs

import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sbaiardi.holdmybeer.R
import com.sbaiardi.holdmybeer.data.ServiceLocator
import com.sbaiardi.holdmybeer.data.api.BeerApiService
import com.sbaiardi.holdmybeer.data.repositories.BeerRepository
import com.sbaiardi.holdmybeer.model.Filter
import com.sbaiardi.holdmybeer.viewmodels.BeerViewModel
import com.sbaiardi.holdmybeer.viewmodels.factory.BeerModelFactory
import kotlinx.android.synthetic.main.dialog_filter_beer_layout.*
import java.lang.Exception
import java.util.*

class FilterBeerDialog: DialogFragment()  {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return inflater.inflate(R.layout.dialog_filter_beer_layout, container, false)

    }


    override fun onStart() {
        super.onStart()
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        btn_filter.setOnClickListener {
            try {
                val beerViewModel: BeerViewModel by viewModels({ requireParentFragment() })
                val startDate = Filter.buildDate(iet_start_month.text.toString().toInt(), iet_start_year.text.toString().toInt())
                val endDate = Filter.buildDate(iet_end_month.text.toString().toInt(), iet_end_year.text.toString().toInt())
                val filter = Filter(startDate, endDate)
                beerViewModel.getFilteredByYears(filter.startDate, filter.endDate, 1, 20)
                dismiss()
            }catch (ex: Exception) {
                Toast.makeText(requireContext(), ex.message.toString(),Toast.LENGTH_LONG).show()
            }


        }
    }





}