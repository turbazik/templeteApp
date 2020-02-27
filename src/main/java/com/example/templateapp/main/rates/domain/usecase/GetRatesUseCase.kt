package com.example.templateapp.main.rates.domain.usecase

import com.example.templateapp.main.rates.domain.RatesRepository
import com.example.templateapp.main.rates.domain.model.RatesEntity

class GetRatesUseCase(
    private val ratesRepository: RatesRepository
) {

    suspend fun getRates(currency: String, amount: Double): RatesEntity {
        ratesRepository.getRates(currency)
    }
}