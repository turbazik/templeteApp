package com.example.templateapp.domain

import com.example.manuel.baseproject.home.beers.domain.usecase.GetBeersUseCase
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
import com.example.templateapp.data.datasource.remote.exceptions.NetworkConnectionException
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import com.example.templateapp.core.datatype.Result
import com.example.templateapp.main.rates.domain.model.RatesEntity
import org.junit.Test
import java.lang.Exception

class GetRatesUseCaseTest {

    private val mockBeersRepository: BeersRepository = mock()
    private val getBeersUseCase = GetBeersUseCase(mockBeersRepository)

    @Test
    fun verifyBusinessErrorWhenRepoMockReturnNetworkError() {
        runBlocking {
            given(mockBeersRepository.getAllBeers())
                .willReturn(Result.error(NetworkConnectionException()))

            val expectedResult = Result.error<Exception>(NetworkConnectionException()).error
            val realResult = getBeersUseCase.execute().error

            Assert.assertEquals(
                expectedResult is NetworkConnectionException,
                realResult is NetworkConnectionException
            )
        }
    }

    @Test
    fun verifyResultWhenRepoMockReturnSuccessState() {
        runBlocking {
            val result = Result.success(RatesEntity(listOf()))
            given(mockBeersRepository.getAllBeers()).willReturn(result)

            val expectedResult = Result.success(BeersEntity(listOf()))
            val realResult = getBeersUseCase.execute()

            Assert.assertEquals(expectedResult, realResult)
        }
    }

    @Test
    fun verifySortedAbvWhenRepoMockReturnUnsortedList() {
        runBlocking {
            val result = Result.success(DomainBeersGenerator.getUnsortedBeers())
            given(mockBeersRepository.getAllBeers()).willReturn(result)

            val expectedResultBeers = Result.success(DomainBeersGenerator.getSortedBeers()).data
            val realResultBeers = getBeersUseCase.execute().data

            realResultBeers?.beers?.forEachIndexed { index, currentRealResult ->
                val currentExpectedResult = expectedResultBeers?.beers?.get(index)?.abv
                val realResult = currentRealResult.abv

                Assert.assertEquals(realResult, currentExpectedResult)
            }
        }
    }

    @Test
    fun verifyUseCaseCallRepository() {
        runBlocking {
            given(mockBeersRepository.getAllBeers())
                .willReturn(Result.success(BeersEntity(listOf())))

            getBeersUseCase.execute()

            verify(mockBeersRepository, times(1)).getAllBeers()
        }
    }
}
