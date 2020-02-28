package com.example.templateapp.data.repository

import com.example.templateapp.core.BaseResponse
import com.example.templateapp.core.datatype.Result
import com.example.templateapp.core.datatype.ResultType
import com.example.templateapp.data.datasource.remote.RatesRemoteDataSource
import com.example.templateapp.data.repository.mapper.ApiToEntityMapper
import com.example.templateapp.main.rates.domain.repository.RatesRepository
import com.example.templateapp.main.rates.domain.model.RatesEntity


class RatesRepositoryImpl(
    private val ratesRemoteDataSource: RatesRemoteDataSource
) : RatesRepository {

    override suspend fun getRates(currency: String): Result<BaseResponse<RatesEntity>> {
        val result = ratesRemoteDataSource.getRates(currency)
        return when (result.resultType) {
            ResultType.SUCCESS -> {
                Result.success(ApiToEntityMapper.map(result.data))
            }
            else -> {
                Result.error(result.error)
            }
        }
    }

}