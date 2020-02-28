package com.example.templateapp.main.rates.domain.usecase

import com.example.templateapp.core.BaseResponse
import com.example.templateapp.core.datatype.Result
import com.example.templateapp.main.rates.domain.repository.RatesRepository
import com.example.templateapp.main.rates.domain.model.RatesEntity

class GetRatesUseCase(
    private val ratesRepository: RatesRepository
) {

    suspend fun getRates(currency: String, amount: Double): Result<BaseResponse<RatesEntity>> {
        return ratesRepository.getRates(currency)
    }
}
