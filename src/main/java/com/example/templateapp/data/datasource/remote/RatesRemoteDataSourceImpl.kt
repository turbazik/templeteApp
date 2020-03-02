package com.example.templateapp.data.datasource.remote

import com.example.templateapp.core.BaseResponse
import com.example.templateapp.core.datatype.Result
import com.example.templateapp.data.datasource.remote.exceptions.handleNetworkExceptions
import com.example.templateapp.data.datasource.remote.mapper.ResponseToApiMapper
import com.example.templateapp.data.datasource.remote.model.api.RatesRemoteData
import com.example.templateapp.data.datasource.remote.model.response.RatesResponse
import com.example.templateapp.data.datasource.remote.retrofit.RatesApiService

class RatesRemoteDataSourceImpl(
    private val ratesApiService: RatesApiService
) : RatesRemoteDataSource {

    override suspend fun getRates(currency: String): Result<BaseResponse<RatesRemoteData>> {
        return try {
            val result = ratesApiService.getRates()
            result.data = RatesResponse(base = null, rates = null)
            result.data!!.rates = result.rates
            result.data!!.base = result.base
            Result.success(ResponseToApiMapper.map(result))
        } catch (ex: Exception) {
            Result.error(handleNetworkExceptions(ex))
        }
    }

}