package com.example.templateapp.data.datasource.remote.exceptions

class BadRequestException(override val message: String?, override val cause: Throwable?) :
    Exception(message, cause)