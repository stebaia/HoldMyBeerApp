package com.sbaiardi.holdmybeer.data.repositories

import com.sbaiardi.holdmybeer.data.api.BeerApiService

class BeerRepository(private val beerApiService: BeerApiService) {
    suspend fun getPagedBeers(url: String)  = beerApiService.getPagedBeers(url)
}