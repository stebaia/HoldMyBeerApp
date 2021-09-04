package com.sbaiardi.holdmybeer.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.sbaiardi.holdmybeer.R
import com.sbaiardi.holdmybeer.data.ServiceLocator
import com.sbaiardi.holdmybeer.data.api.BeerApiService
import com.sbaiardi.holdmybeer.data.repositories.BeerRepository
import com.sbaiardi.holdmybeer.model.Beer
import com.sbaiardi.holdmybeer.utils.adapters.BeerAdapter
import com.sbaiardi.holdmybeer.utils.listeners.EndlessRecyclerViewScrollListener
import com.sbaiardi.holdmybeer.viewmodels.BeerViewModel
import com.sbaiardi.holdmybeer.viewmodels.factory.BeerModelFactory
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
    private val initial_page=1
    private val per_page=20
    private lateinit var listener: BottomSheetCallback
    private lateinit var beerViewModel: BeerViewModel
    private var flagNameChanged = false
    private var flag = false
    private var nameSearched: String = ""
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        beerViewModel = ViewModelProvider(
                this, BeerModelFactory(
                BeerRepository(
                        ServiceLocator.getRetrofit().create(
                                BeerApiService::class.java
                        )
                )
        )
        ).get(BeerViewModel::class.java)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        val beerListAdapter = BeerAdapter{ beer -> adapterOnClick(beer) }
        val layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
        )

        val scrollListener = object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                beerViewModel.getSearchBeersByName(nameSearched, page + 1, per_page)
            }
        }
        recyler_view_beer.addOnScrollListener(scrollListener)
        recyler_view_beer.adapter = beerListAdapter
        recyler_view_beer.layoutManager = layoutManager
        var mutableListBeer: MutableList<Beer> = ArrayList()

        iet_search_beer.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                flagNameChanged = true
                if (s.length > 2){
                    nameSearched = iet_search_beer.text.toString()
                    beerViewModel.getSearchBeersByName(iet_search_beer.text.toString(),initial_page, per_page)
                }else if (s.isEmpty()){
                    beerViewModel.getSearchBeersByName("",initial_page, per_page)
                }
            }
        })
        beerViewModel.beers.observe(viewLifecycleOwner, {
            it.let {
                if (flagNameChanged){
                    mutableListBeer.clear()
                    flagNameChanged = false
                }
                mutableListBeer.addAll(it)
                beerListAdapter.submitList(mutableListBeer)
                beerListAdapter.notifyDataSetChanged()
            }
        })
        beerViewModel.getSearchBeersByName("",initial_page, per_page)
    }

    private fun configureBackdrop(beer: Beer){
        val fragmentContainer = childFragmentManager.findFragmentById(R.id.container)
        fragmentContainer?.view?.let {
            BottomSheetBehavior.from(it).let { bs ->
                bs.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                    override fun onSlide(bottomSheet: View, slideOffset: Float) {}

                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                        listener.onStateChanged(bottomSheet, newState, beer)
                    }
                })

                bs.state = BottomSheetBehavior.STATE_COLLAPSED
                bottomSheetBehavior = bs
            }
        }
    }

    fun setOnBottomSheetCallback(onBottomSheetCallbacks: BottomSheetCallback) {
        this.listener = onBottomSheetCallbacks
    }

    fun closeBottomSheet() {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    fun openBottomSheet() {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }


    private fun adapterOnClick(beer: Beer) {
        configureBackdrop(beer)
        openBottomSheet()
    }
}



interface BottomSheetCallback {
    fun onStateChanged(bottomSheet: View, newState: Int, beer: Beer)
}