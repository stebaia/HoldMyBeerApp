package com.sbaiardi.holdmybeer.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sbaiardi.holdmybeer.data.repositories.BeerRepository
import com.sbaiardi.holdmybeer.viewmodels.BeerViewModel
import java.lang.IllegalArgumentException

class BeerModelFactory(private val beerRepository: BeerRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(BeerViewModel::class.java)){
            BeerViewModel(this.beerRepository) as T
        }else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}