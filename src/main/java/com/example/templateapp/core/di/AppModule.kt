package com.example.templateapp.core.di


import com.example.templateapp.core.BaseActivity
import com.example.templateapp.core.provider.DialogManager
import org.koin.dsl.module

val appModule = module {
    factory { (activity: BaseActivity?) -> DialogManager(activity) }
}
