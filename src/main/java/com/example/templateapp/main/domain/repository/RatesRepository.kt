package com.example.templateapp.main.domain.repository

import com.example.templateapp.core.datatype.Result
import com.example.templateapp.main.domain.model.RatesEntity

interface RatesRepository {
    suspend fun getRates(currency: String): Result<RatesEntity>?
}