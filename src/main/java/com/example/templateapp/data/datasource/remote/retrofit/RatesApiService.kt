package com.example.templateapp.data.datasource.remote.retrofit

import com.example.templateapp.data.datasource.remote.model.response.RatesResponse
import retrofit2.http.GET

interface RatesApiService {
    @GET("beers?")
    suspend fun getRates(
        currency: String
    ): RatesResponse?
}
