package com.example.templateapp.main.di

import com.example.templateapp.main.rates.domain.usecase.GetRatesUseCase
import com.example.templateapp.main.rates.vm.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val ratesModule = module {
    factory { GetRatesUseCase(ratesRepository = get()) }
    viewModel {
        MainViewModel(
            getRatesUseCase = get()
        )
    }
}