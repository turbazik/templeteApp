package com.example.templateapp.features.rates.domain.di

import com.example.templateapp.features.rates.domain.usecase.GetRatesUseCase
import org.koin.dsl.module

val ratesDomainModule = module {
    factory {
        GetRatesUseCase(
            ratesRepository = get()
        )
    }
}