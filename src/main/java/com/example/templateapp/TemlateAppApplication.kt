package com.example.templateapp

import android.app.Application
import com.example.templateapp.core.di.appModule
import com.example.templateapp.data.di.ratesDataModule
import com.example.templateapp.data.di.retrofitModule
import com.example.templateapp.main.di.ratesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber


class TemplateAppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@TemplateAppApplication)
            modules(
                listOf(
                    retrofitModule,
                    ratesModule,
                    ratesDataModule,
                    appModule
                )
            )
        }
    }
}