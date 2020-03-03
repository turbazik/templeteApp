package com.example.templateapp.data.datasource.remote.exceptions

class NetworkConnectionException(override val message: String?, override val cause: Throwable?) :
    Exception(message, cause)