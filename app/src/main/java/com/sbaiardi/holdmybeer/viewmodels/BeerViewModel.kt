package com.sbaiardi.holdmybeer.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sbaiardi.holdmybeer.data.repositories.BeerRepository
import com.sbaiardi.holdmybeer.model.Beer
import com.sbaiardi.holdmybeer.model.Filter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class BeerViewModel(private val beerRepository: BeerRepository): ViewModel() {
    private val _beers = MutableLiveData<MutableList<Beer>>()
    val beers: LiveData<MutableList<Beer>> = _beers

    val filters = MutableLiveData<Set<Filter>>()
    private val _beersFiltered = MutableLiveData<MutableList<Beer>>()
    val filteredList: LiveData<MutableList<Beer>> = _beersFiltered

    fun getFilteredByYears(startDate: String, endDate:String, page: Int, per_page: Int) {
        CoroutineScope(Main).launch(Dispatchers.IO) {
            val response = beerRepository.getFilterByYears(startDate, endDate,page, per_page)
            if (response.isSuccessful){
                print(response.body())
                _beersFiltered.postValue(response.body())
            }else{
                print(response.errorBody())
            }
        }
    }



    fun getSearchBeersByName(name: String,page: Int, per_page: Int) {
        val replacedName = name.replace(" ", "_")
        CoroutineScope(Main).launch(Dispatchers.IO) {

            val response = if(name.length > 2) beerRepository.getSearchBeersByName(replacedName,page, per_page) else beerRepository.getPagedBeers(page, per_page)
            if (response.isSuccessful){
                print(response.body())
                _beers.postValue(response.body())
            }else{
                print(response.errorBody())
            }
        }
    }

}