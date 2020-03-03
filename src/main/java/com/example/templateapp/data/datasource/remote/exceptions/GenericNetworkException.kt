package com.example.templateapp.data.datasource.remote.exceptions

import java.lang.Exception

class GenericNetworkException(override val message: String?, override val cause: Throwable?) :
    Exception(message, cause)