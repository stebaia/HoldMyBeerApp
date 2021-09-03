package com.sbaiardi.holdmybeer.data.repositories

import com.sbaiardi.holdmybeer.data.api.BeerApiService

class BeerRepository(private val beerApiService: BeerApiService) {
    suspend fun getPagedBeers(page: Int, per_page: Int)  = beerApiService.getPagedBeers(page, per_page)
}