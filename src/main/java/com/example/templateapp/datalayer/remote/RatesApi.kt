package com.example.templateapp.datalayer.remote

import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface RatesApi {
    @GET("v02/artery/ping")
    suspend fun arteryPing(@Body params: RequestBody): Response<BaseResponse<Ping>>

}
