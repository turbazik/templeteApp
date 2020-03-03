package com.example.templateapp.data.datasource.local

import com.example.templateapp.data.datasource.local.model.RatesLocalData

interface RatesLocalDataSource {
    suspend fun getRates(currency: String): RatesLocalData
    suspend fun saveRates(rates: RatesLocalData)
}