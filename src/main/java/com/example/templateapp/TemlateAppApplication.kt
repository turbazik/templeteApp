package com.example.templateapp

import android.app.Application
import com.example.templateapp.data.di.networkModule
import com.example.templateapp.main.di.mainViewModelModule
import com.example.templateapp.main.di.ratesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class BaseProjectApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@BaseProjectApplication)
            modules(
                listOf(
                    ratesModule,
                    mainViewModelModule,
                    networkModule
                )
            )
        }
    }
}