package com.example.templateapp.features.rates.domain.repository

import com.example.templateapp.core.BaseResponse
import com.example.templateapp.core.datatype.Result
import com.example.templateapp.features.rates.domain.model.RatesEntity

interface RatesRepository {
    suspend fun getRates(currency: String): Result<BaseResponse<RatesEntity>>
}