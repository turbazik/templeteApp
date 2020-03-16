package com.example.templateapp.features.rates.data.datasource.remote

import com.example.templateapp.core.BaseResponse
import com.example.templateapp.core.datatype.Result
import com.example.templateapp.features.rates.data.datasource.remote.model.api.RatesRemoteData

interface RatesRemoteDataSource {
    suspend fun getRates(currency: String): Result<BaseResponse<RatesRemoteData>>
}