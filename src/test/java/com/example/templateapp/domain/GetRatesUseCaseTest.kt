package com.example.templateapp.domain

import com.example.templateapp.core.BaseResponse
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
import com.example.templateapp.data.datasource.remote.exceptions.NetworkConnectionException
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import com.example.templateapp.core.datatype.Result
import com.example.templateapp.main.rates.domain.model.RatesEntity
import com.example.templateapp.main.rates.domain.repository.RatesRepository
import com.example.templateapp.main.rates.domain.usecase.GetRatesUseCase
import org.junit.Test
import java.lang.Exception

class GetRatesUseCaseTest {

    private val ratesRepository: RatesRepository = mock()
    private val getRatesUseCase = GetRatesUseCase(ratesRepository)

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
            val result = Result.success(BaseResponse<RatesEntity>())
            given(ratesRepository.getRates("EUR")).willReturn(result)

            val expectedResult = Result.success(BaseResponse<RatesEntity>())
            val realResult = getRatesUseCase.getRates("EUR", 1.0)

            Assert.assertEquals(expectedResult, realResult)
        }
    }
//
//    @Test
//    fun verifySortedAbvWhenRepoMockReturnUnsortedList() {
//        runBlocking {
//            val result = Result.success(DomainBeersGenerator.getUnsortedBeers())
//            given(ratesRepository.getAllBeers()).willReturn(result)
//
//            val expectedResultBeers = Result.success(DomainBeersGenerator.getSortedBeers()).data
//            val realResultBeers = getRatesUseCase.execute().data
//
//            realResultBeers?.beers?.forEachIndexed { index, currentRealResult ->
//                val currentExpectedResult = expectedResultBeers?.beers?.get(index)?.abv
//                val realResult = currentRealResult.abv
//
//                Assert.assertEquals(realResult, currentExpectedResult)
//            }
//        }
//    }
//
//    @Test
//    fun verifyUseCaseCallRepository() {
//        runBlocking {
//            given(ratesRepository.getAllBeers())
//                .willReturn(Result.success(BeersEntity(listOf())))
//
//            getRatesUseCase.execute()
//
//            verify(ratesRepository, times(1)).getAllBeers()
//        }
//    }
}
