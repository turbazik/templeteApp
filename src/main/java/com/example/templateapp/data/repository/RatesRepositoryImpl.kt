package com.example.templateapp.data.repository

import com.example.templateapp.core.BaseResponse
import com.example.templateapp.core.datatype.Result
import com.example.templateapp.core.datatype.ResultType
import com.example.templateapp.data.datasource.local.RatesLocalDataSource
import com.example.templateapp.data.datasource.remote.RatesRemoteDataSource
import com.example.templateapp.data.datasource.remote.exceptions.NetworkConnectionException
import com.example.templateapp.data.repository.mapper.ApiToEntityMapper
import com.example.templateapp.data.repository.mapper.ApiToLocalMapper
import com.example.templateapp.data.repository.mapper.LocalToEntityMapper
import com.example.templateapp.main.rates.domain.repository.RatesRepository
import com.example.templateapp.main.rates.domain.model.RatesEntity


class RatesRepositoryImpl(
    private val ratesRemoteDataSource: RatesRemoteDataSource,
    private val ratesLocalDataSource: RatesLocalDataSource
) : RatesRepository {

    override suspend fun getRates(currency: String): Result<BaseResponse<RatesEntity>> {
        val result = ratesRemoteDataSource.getRates(currency)
        return when (result.resultType) {
            ResultType.SUCCESS -> {
                val data = result.data
                if (data?.success!!)
                    ratesLocalDataSource.saveRates(ApiToLocalMapper.map(data.data))
                Result.success(ApiToEntityMapper.map(data))
            }
            else -> {
                if (result.error is NetworkConnectionException) {
                    val data =
                        LocalToEntityMapper.map(ratesLocalDataSource.getRates(currency))
                    if (data != null) {
                        return Result.success(data)
                    }
                }
                Result.error(result.error)
            }
        }
    }

}