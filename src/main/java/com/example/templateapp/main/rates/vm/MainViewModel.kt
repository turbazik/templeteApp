package com.example.templateapp.main.rates.vm

import androidx.lifecycle.MutableLiveData
import com.example.templateapp.core.BaseViewModel
import com.example.templateapp.main.rates.domain.usecase.GetRatesUseCase
import com.example.templateapp.main.rates.vm.mapper.RatesEntityToUiMapper
import com.example.templateapp.main.rates.vm.model.RatesUi
import com.example.templateapp.util.Event

class MainViewModel(
    private val getRatesUseCase: GetRatesUseCase
) : BaseViewModel() {

    val ratesMutableLiveData = MutableLiveData<Event<RatesUi>>()
    val text = MutableLiveData<String>()
    var initialized = false

    fun fetchRates(currency: String, amount: Double) {
        apiCall(
            {
                getRatesUseCase.getRates(currency, amount)
            },
            success = { ratesEntity ->
                ratesMutableLiveData.postValue(Event(RatesEntityToUiMapper.map(ratesEntity)))
                var s = ""
                for (i in RatesEntityToUiMapper.map(ratesEntity).rates!!) {
                    s += i.name + " "
                }
                text.postValue(s)
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