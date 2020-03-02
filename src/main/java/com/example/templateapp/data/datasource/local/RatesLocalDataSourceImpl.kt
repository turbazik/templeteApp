package com.example.templateapp.data.datasource.local

import com.example.templateapp.data.datasource.local.model.RatesLocalData
import com.example.templateapp.data.datasource.local.room.RatesDao

class RatesLocalDataSourceImpl(
    private val ratesDao: RatesDao
) : RatesLocalDataSource {

    override suspend fun saveRates(rates: List<RatesLocalData>) {
        ratesDao.saveRates(rates)
    }

    override suspend fun getRates(currency: String): RatesLocalData {
        return ratesDao.getRates(currency)
    }

}