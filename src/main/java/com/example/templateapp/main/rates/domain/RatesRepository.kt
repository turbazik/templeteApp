package com.example.templateapp.main.rates.domain

import com.example.templateapp.core.datatype.Result
import com.example.templateapp.main.rates.domain.model.RatesEntity

interface RatesRepository {
    suspend fun getRates(currency: String): Result<RatesEntity>?
}