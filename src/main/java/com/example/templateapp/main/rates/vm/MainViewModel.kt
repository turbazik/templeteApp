package com.example.templateapp.main.rates.vm

import com.example.templateapp.core.BaseViewModel
import com.example.templateapp.main.rates.domain.usecase.GetRatesUseCase
import com.example.templateapp.util.Event

class MainViewModel(
    private val getRatesUseCase: GetRatesUseCase
) : BaseViewModel() {

    fun fetchRates(currency: String, amount: Double) {
        apiCall(
            { getRatesUseCase.getRates(currency, amount) },
            success = { questions ->
                createQuestionUiModelsEvent.value = Event(questions)
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