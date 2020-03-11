package com.example.templateapp.domain.utils

import com.example.templateapp.core.BaseResponse
import com.example.templateapp.data.datasource.remote.model.api.Rates
import com.example.templateapp.main.rates.domain.model.RatesEntity


object DomainRatesGenerator {

    fun getBaseRates(): BaseResponse<RatesEntity> {
        val response = BaseResponse<RatesEntity>()
        val list = listOf(
            Rates("AED", 4.165951),
            Rates("AFN", 86.356484),
            Rates("ALL", 123.18469),
            Rates("AMD", 549.561013),
            Rates("ANG", 2.036619)
        )
        response.data = RatesEntity("EUR", list)
        return response
    }

    fun getCleanRates(): BaseResponse<RatesEntity> {
        val response = BaseResponse<RatesEntity>()
        val list = listOf(
            Rates("AED", 4.165951 * 2.0),
            Rates("AFN", 86.356484 * 2.0),
            Rates("ALL", 123.18469 * 2.0),
            Rates("AMD", 549.561013 * 2.0),
            Rates("ANG", 2.036619 * 2.0)
        )
        response.data = RatesEntity("EUR", list)
        return response
    }
}