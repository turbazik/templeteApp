package com.example.templateapp.features.rates.data.datasource.di

import androidx.room.Room
import com.example.templateapp.features.rates.data.datasource.local.RatesLocalDataSource
import com.example.templateapp.features.rates.data.datasource.local.RatesLocalDataSourceImpl
import com.example.templateapp.features.rates.data.datasource.local.db.RatesDatabase
import com.example.templateapp.features.rates.data.datasource.remote.RatesRemoteDataSource
import com.example.templateapp.features.rates.data.datasource.remote.RatesRemoteDataSourceImpl
import com.example.templateapp.features.rates.data.datasource.remote.retrofit.RatesApiService
import com.example.templateapp.features.rates.data.repository.RatesRepositoryImpl
import com.example.templateapp.features.rates.domain.repository.RatesRepository
import com.example.templateapp.features.rates.domain.usecase.GetRatesUseCase
import com.example.templateapp.features.rates.presentation.vm.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit


val ratesDataModule = module {
    single {
        provideBeersApiService(
            retrofit = get()
        )
    }
    single<RatesRemoteDataSource> {
        RatesRemoteDataSourceImpl(
            ratesApiService = get()
        )
    }
    single {
        Room.databaseBuilder(get(), RatesDatabase::class.java, "rates-db")
            .build()
    }
    single { get<RatesDatabase>().ratesDao() }
    single<RatesLocalDataSource> {
        RatesLocalDataSourceImpl(
            ratesDao = get()
        )
    }
    single<RatesRepository> {
        RatesRepositoryImpl(
            ratesRemoteDataSource = get(),
            ratesLocalDataSource = get()
        )
    }
}

private fun provideBeersApiService(retrofit: Retrofit): RatesApiService =
    retrofit.create(RatesApiService::class.java)
