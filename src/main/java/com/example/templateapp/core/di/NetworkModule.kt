package com.example.templateapp.core.di


import com.example.templateapp.BuildConfig
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
    single {
        provideOkHttpClient(
            get()
        )
    }
    single {
        provideRetrofitInstance(
            get()
        )
    }
}

private fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(URL_BASE)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()


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
