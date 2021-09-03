package com.sbaiardi.holdmybeer.data.api

import com.sbaiardi.holdmybeer.model.BeerResponse
import retrofit2.Response
import retrofit2.http.GET

interface BeerApiService {
    @GET("")
    suspend fun getPositivePercentage(): BeerResponse
}