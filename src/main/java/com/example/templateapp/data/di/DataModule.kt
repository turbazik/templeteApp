package com.example.templateapp.data.di

import com.example.templateapp.BuildConfig
import com.example.templateapp.data.datasource.remote.RatesRemoteDataSource
import com.example.templateapp.data.datasource.remote.RatesRemoteDataSourceImpl
import com.example.templateapp.data.datasource.remote.retrofit.RatesApiService
import com.example.templateapp.data.repository.RatesRepositoryImpl
import com.example.templateapp.main.rates.domain.repository.RatesRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val TIME_OUT_SECOND = 60L
private const val URL_BASE = "http://data.fixer.io/"


val retrofitModule = module {
    single { provideHttpLoggingInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideRetrofitInstance(get()) }
}

val ratesApiModule = module {
    factory { provideBeersApiService(retrofit = get()) }
    single {
        RatesRemoteDataSourceImpl(
            ratesApiService = get()
        ) as RatesRemoteDataSource
    }
    single {
        RatesRepositoryImpl(
            ratesRemoteDataSource = get()
        ) as RatesRepository
    }
}

private fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(URL_BASE)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()


private fun provideBeersApiService(retrofit: Retrofit): RatesApiService =
    retrofit.create(RatesApiService::class.java)

fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .connectTimeout(TIME_OUT_SECOND, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT_SECOND, TimeUnit.SECONDS)
        .writeTimeout(TIME_OUT_SECOND, TimeUnit.SECONDS)
        .build()
}

fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = if (BuildConfig.DEBUG)
        HttpLoggingInterceptor.Level.BODY
    else
        HttpLoggingInterceptor.Level.NONE
    return interceptor
}


