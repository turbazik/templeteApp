package com.example.templateapp.data.datasource.remote.exceptions

import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import java.net.UnknownHostException

fun handleNetworkExceptions(ex: Exception): Exception {
    return when (ex) {
        is IOException -> NetworkConnectionException(
            message = "Интернет недоступен",
            cause = Throwable("Проверьте соединение и\nповторите попытку")
        )
        is UnknownHostException -> NetworkConnectionException(
            message = "Сервис временно\nнедоступен",
            cause = Throwable("Попробуйте позже")
        )
        is HttpException -> apiErrorFromCodeException(
            ex.code()
        )
        else -> GenericNetworkException(
            message = "Сервис временно\nнедоступен",
            cause = Throwable("Попробуйте позже")
        )
    }
}

private fun apiErrorFromCodeException(code: Int): Exception {
    return if (code == 400) {
        BadRequestException(message = "Сервис временно\nнедоступен",
            cause = Throwable("Попробуйте позже"))
    } else {
        GenericNetworkException(message = "Сервис временно\nнедоступен",
            cause = Throwable("Попробуйте позже"))
    }
}