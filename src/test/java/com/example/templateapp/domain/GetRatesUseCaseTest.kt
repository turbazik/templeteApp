package com.example.templateapp.domain

import com.example.templateapp.core.BaseResponse
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
import com.example.templateapp.features.rates.data.datasource.remote.exceptions.NetworkConnectionException
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import com.example.templateapp.core.datatype.Result
import com.example.templateapp.domain.utils.DomainRatesGenerator
import com.example.templateapp.features.rates.domain.model.RatesEntity
import com.example.templateapp.features.rates.domain.repository.RatesRepository
import com.example.templateapp.features.rates.domain.usecase.GetRatesUseCase
import org.junit.Test
import java.lang.Exception

class GetRatesUseCaseTest {

    private val ratesRepository: RatesRepository = mock()
    private val getRatesUseCase =
        GetRatesUseCase(
            ratesRepository
        )

    @Test
    fun `verify business error when repo mock return networkError`() {
        runBlocking {
            given(ratesRepository.getRates("EUR"))
                .willReturn(
                    Result.error(
                        NetworkConnectionException()
                    )
                )

            val expectedResult = Result.error<Exception>(
                NetworkConnectionException()
            ).error
            val realResult = getRatesUseCase.getRates("EUR", 1.0).error

            Assert.assertEquals(
                expectedResult is NetworkConnectionException,
                realResult is NetworkConnectionException
            )
        }
    }

    @Test
    fun `verify result when repo mock return success state`() {
        runBlocking {
            val response = BaseResponse<RatesEntity>()
            val result = Result.success(response)
            given(ratesRepository.getRates("EUR")).willReturn(result)

            val expectedResult = Result.success(response)
            val realResult = getRatesUseCase.getRates("EUR", 1.0)

            Assert.assertEquals(expectedResult, realResult)
        }
    }


    @Test
    fun `verify clean rates when repo mock return base rates`() {
        runBlocking {
            val result = Result.success(DomainRatesGenerator.getBaseRates())
            given(ratesRepository.getRates("EUR")).willReturn(result)

            val expectedResultBeers = Result.success(DomainRatesGenerator.getCleanRates()).data
            val realResultBeers = getRatesUseCase.getRates("EUR", 2.0).data

            realResultBeers?.data?.rates?.forEachIndexed { index, currentRealResult ->
                val currentExpectedResult = expectedResultBeers?.data?.rates?.get(index)
                Assert.assertEquals(currentRealResult, currentExpectedResult)
            }
        }
    }

    @Test
    fun `verify usecase call repository`() {
        runBlocking {
            given(ratesRepository.getRates("EUR"))
                .willReturn(Result.success(BaseResponse()))

            getRatesUseCase.getRates("EUR", 1.0)

            verify(ratesRepository, times(1)).getRates("EUR")
        }
    }
}
