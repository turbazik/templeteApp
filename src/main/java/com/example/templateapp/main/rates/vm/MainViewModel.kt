package com.example.templateapp.main.rates.vm

import androidx.lifecycle.ViewModel
import com.example.templateapp.core.BaseViewModel
import com.example.templateapp.main.rates.domain.usecase.GetRatesUseCase

class MainViewModel(
    private val getRatesUseCase: GetRatesUseCase
) : BaseViewModel() {
    override fun getTag(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}