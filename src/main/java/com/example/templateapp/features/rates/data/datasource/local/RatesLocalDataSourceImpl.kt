package com.example.templateapp.features.rates.data.datasource.local

import com.example.templateapp.features.rates.data.datasource.local.model.RatesLocalData
import com.example.templateapp.features.rates.data.datasource.local.room.RatesDao
import timber.log.Timber
import java.lang.Exception

class RatesLocalDataSourceImpl(
    private val ratesDao: RatesDao
) : RatesLocalDataSource {

    override suspend fun saveRates(rates: RatesLocalData) {
        try {
            ratesDao.saveRates(rates)
        }catch (e : Exception){
            Timber.e(e)
        }

    }

    override suspend fun getRates(currency: String): RatesLocalData {
        return ratesDao.getRates(currency)
    }

}