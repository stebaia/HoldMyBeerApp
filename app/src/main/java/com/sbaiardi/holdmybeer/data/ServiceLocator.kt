package com.sbaiardi.holdmybeer.data

import com.sbaiardi.holdmybeer.data.api.BeerApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

object ServiceLocator {
    private const val BASE_URL = ""

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: BeerApiService = getRetrofit().create(BeerApiService::class.java)
}