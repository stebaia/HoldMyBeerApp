package com.sbaiardi.holdmybeer.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sbaiardi.holdmybeer.R
import com.sbaiardi.holdmybeer.data.ServiceLocator
import com.sbaiardi.holdmybeer.data.api.BeerApiService
import com.sbaiardi.holdmybeer.data.repositories.BeerRepository
import com.sbaiardi.holdmybeer.model.Beer
import com.sbaiardi.holdmybeer.utils.adapters.BeerAdapter
import com.sbaiardi.holdmybeer.viewmodels.BeerViewModel
import com.sbaiardi.holdmybeer.viewmodels.factory.BeerModelFactory
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private val page=1
    private val per_page=20

    private lateinit var beerViewModel: BeerViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        beerViewModel = ViewModelProvider(this, BeerModelFactory(BeerRepository(ServiceLocator.getRetrofit().create(BeerApiService::class.java))
        )).get(BeerViewModel::class.java)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        val beerListAdapter = BeerAdapter{ beer -> adapterOnClick(beer) }
        recyler_view_beer.adapter = beerListAdapter
        recyler_view_beer.layoutManager = LinearLayoutManager(requireContext())
        beerViewModel.beers.observe(viewLifecycleOwner, {
            it.let {
                Log.d("Loggin_beer",it.toString())
                beerListAdapter.submitList(it as MutableList<Beer>)
            }
        })
        beerViewModel.getPagedBeers(page, per_page)
    }

    private fun adapterOnClick(beer: Beer) {


    }
}