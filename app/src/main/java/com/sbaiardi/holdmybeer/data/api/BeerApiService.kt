package com.sbaiardi.holdmybeer.data.api

import com.sbaiardi.holdmybeer.model.BeerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface BeerApiService {
    @GET
    suspend fun getPagedBeers(@Url url: String): BeerResponse
}