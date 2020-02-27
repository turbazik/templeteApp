package com.example.templateapp.main.di

import com.example.templateapp.main.rates.domain.usecase.GetRatesUseCase
import org.koin.dsl.module


val ratesModule = module {
    factory { GetRatesUseCase(ratesRepository = get()) }
}