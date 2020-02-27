package com.example.templateapp.data.datasource.remote

import com.example.templateapp.core.datatype.Result
import com.example.templateapp.data.datasource.remote.model.api.RatesRemoteData

interface RatesRemoteDataSource {
    suspend fun getRates(currency: String): Result<RatesRemoteData>?
}