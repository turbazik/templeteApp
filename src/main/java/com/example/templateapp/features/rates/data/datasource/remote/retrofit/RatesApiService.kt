package com.example.templateapp.features.rates.data.datasource.remote.retrofit

import com.example.templateapp.core.BaseResponse
import com.example.templateapp.features.rates.data.datasource.remote.model.response.RatesResponse
import retrofit2.http.GET

interface RatesApiService {
    @GET("api/latest?access_key=20378b33bf2657b460d5375e63c1c5fc&format=1")
    suspend fun getRates(
    ): BaseResponse<RatesResponse>
}
