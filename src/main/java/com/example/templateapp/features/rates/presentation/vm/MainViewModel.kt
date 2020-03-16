package com.example.templateapp.features.rates.presentation.vm

import androidx.lifecycle.MutableLiveData
import com.example.templateapp.core.BaseViewModel
import com.example.templateapp.features.rates.domain.usecase.GetRatesUseCase
import com.example.templateapp.features.rates.presentation.vm.mapper.RatesEntityToUiMapper
import com.example.templateapp.features.rates.presentation.vm.model.RatesUi
import com.example.templateapp.util.FieldItem

class MainViewModel(
    private val getRatesUseCase: GetRatesUseCase
) : BaseViewModel() {

    val ratesMutableLiveData = MutableLiveData<RatesUi>()
    val currency = FieldItem("")
    var initialized = false

    fun fetchRates(currency: String, amount: Double) {
        apiCall(
            {
                getRatesUseCase.getRates(currency, amount)
            },
            success = { ratesEntity ->
                ratesMutableLiveData.postValue(RatesEntityToUiMapper.map(ratesEntity))
                initialized = true
            },
            loading = {
                loading.postValue(true)
            },
            onComplete = {
                loading.postValue(false)
            }

        )

    }


    override fun getTag(): String = this.javaClass.simpleName
}