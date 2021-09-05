package com.sbaiardi.holdmybeer.data.api

import com.sbaiardi.holdmybeer.model.Beer
import com.sbaiardi.holdmybeer.model.BeerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface BeerApiService {
    @GET("beers")
    suspend fun getPagedBeers(@Query("page") page: Int, @Query("per_page") per_page: Int): Response<MutableList<Beer>>

    @GET("beers")
    suspend fun getSearchBeersByName(@Query("beer_name") beer_name: String, @Query("page") page: Int, @Query("per_page") per_page: Int): Response<MutableList<Beer>>

    @GET("beers")
    suspend fun getBeerFilteredByYears(@Query("brewed_after") brewed_after: String, @Query("brewed_before") brewed_before: String, @Query("page") page: Int, @Query("per_page") per_page: Int): Response<MutableList<Beer>>

}