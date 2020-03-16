package com.example.templateapp.features.rates.data.datasource.remote.exceptions

import java.lang.Exception

class GenericNetworkException :
    Exception() {
    override val message: String?
        get() = "Сервис временно\nнедоступен"
    override val cause: Throwable?
        get() = Throwable("Попробуйте позже")
}