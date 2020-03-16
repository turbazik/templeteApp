package com.example.templateapp.features.rates.data.datasource.remote.exceptions

class BadRequestException :
    Exception() {
    override val message: String?
        get() = "Сервис временно\nнедоступен"
    override val cause: Throwable?
        get() = Throwable("Попробуйте позже")
}