package com.sbaiardi.holdmybeer.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sbaiardi.holdmybeer.data.repositories.BeerRepository
import com.sbaiardi.holdmybeer.model.Beer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class BeerViewModel(private val beerRepository: BeerRepository): ViewModel() {
    private val _beers = MutableLiveData<List<Beer>>()
    val beers: LiveData<List<Beer>> = _beers

    fun getPagedBeers(page: Int, per_page: Int) {
        CoroutineScope(Main).launch(Dispatchers.IO) {
            val response = beerRepository.getPagedBeers(page, per_page)
            if (response.isSuccessful){
                print(response.body())
                _beers.postValue(response.body())
            }else{
                print(response.errorBody())
            }
        }
    }
}