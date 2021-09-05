package com.sbaiardi.holdmybeer.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sbaiardi.holdmybeer.R
import com.sbaiardi.holdmybeer.model.Beer
import kotlinx.android.synthetic.main.detail_beer_bottomsheet_layout.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DetailBeerFragment : BottomSheetDialogFragment(), BottomSheetCallback {
    private var currentState: Int = BottomSheetBehavior.STATE_EXPANDED
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        (parentFragment as FirstFragment).setOnBottomSheetCallback(this)
        return inflater.inflate(R.layout.detail_beer_bottomsheet_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        img_arrow_down.setOnClickListener {
            (parentFragment as FirstFragment).closeBottomSheet()
        }
    }

    override fun onStateChanged(bottomSheet: View, newState: Int, beer: Beer) {
        currentState = newState
        buildDetailBeer(beer)
        when (newState) {
            BottomSheetBehavior.STATE_EXPANDED -> {
                img_arrow_down.visibility = View.VISIBLE
                //filterImage.setImageResource(R.drawable.ic_baseline_filter_list_24)
            }
            BottomSheetBehavior.STATE_COLLAPSED -> {
                img_arrow_down.visibility = View.GONE
                //filterImage.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
            }
        }

    }

    private fun buildDetailBeer(beer: Beer){
        txt_name.text = beer.name
        txt_description.text = beer.description
        txt_abv.text = beer.abv.toString()
        txt_first_brewed.text = beer.first_brewed
        Glide.with(img_beer.context).load(beer.image_url).into(img_beer)
    }


}