package com.example.templateapp.features.rates.presentation.di

import com.example.templateapp.features.rates.presentation.vm.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val ratesPresentationModule = module {
    viewModel {
        MainViewModel(
            getRatesUseCase = get()
        )
    }
}