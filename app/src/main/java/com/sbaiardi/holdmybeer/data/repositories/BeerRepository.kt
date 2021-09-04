package com.sbaiardi.holdmybeer.data.repositories

import com.sbaiardi.holdmybeer.data.api.BeerApiService

class BeerRepository(private val beerApiService: BeerApiService) {
    suspend fun getPagedBeers(page: Int, per_page: Int)  = beerApiService.getPagedBeers(page, per_page)

    suspend fun getSearchBeersByName(beer_name: String,page: Int, per_page: Int)  = beerApiService.getSearchBeersByName(beer_name,page, per_page)
}