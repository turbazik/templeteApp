package com.example.templateapp.main.rates.vm

import com.example.templateapp.core.BaseViewModel
import com.example.templateapp.main.rates.domain.usecase.GetRatesUseCase
import timber.log.Timber

class MainViewModel(
    private val getRatesUseCase: GetRatesUseCase
) : BaseViewModel() {

    fun fetchRates(currency: String, amount: Double) {
        apiCall(
            {
                getRatesUseCase.getRates(currency, amount)
            },
            success = {
                for (i in it.rates!!) {
                    Timber.d(i.name)
                }
            },
            loading = {
                loading.value = true
            },
            onComplete = {
                loading.value = false
            }

        )

    }


    override fun getTag(): String = this.javaClass.simpleName
}