package com.example.templateapp.features.rates.domain.usecase

import com.example.templateapp.core.BaseResponse
import com.example.templateapp.core.datatype.Result
import com.example.templateapp.core.datatype.ResultType
import com.example.templateapp.features.rates.domain.repository.RatesRepository
import com.example.templateapp.features.rates.domain.model.RatesEntity

class GetRatesUseCase(
    private val ratesRepository: RatesRepository
) {

    suspend fun getRates(currency: String, amount: Double): Result<BaseResponse<RatesEntity>> {
        val result = ratesRepository.getRates(currency)
        if (result.resultType == ResultType.SUCCESS && result.data?.success!! && result.data.data?.rates != null) {
            result.data.data?.rates!!.forEach { rates -> rates.value = amount * rates.value!! }
        }
        return result
    }
}
