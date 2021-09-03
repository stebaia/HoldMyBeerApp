package com.sbaiardi.holdmybeer

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sbaiardi.holdmybeer.data.ServiceLocator
import com.sbaiardi.holdmybeer.data.api.BeerApiService
import com.sbaiardi.holdmybeer.data.repositories.BeerRepository
import com.sbaiardi.holdmybeer.model.Beer
import com.sbaiardi.holdmybeer.viewmodels.BeerViewModel
import com.sbaiardi.holdmybeer.viewmodels.factory.BeerModelFactory
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

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

       button_first.setOnClickListener {
            beerViewModel.beers.observe(viewLifecycleOwner, {
                it.let {
                    Log.d("Loggin_beer",it.toString())
                }
            })
           beerViewModel.getPagedBeers()
       }
    }
}