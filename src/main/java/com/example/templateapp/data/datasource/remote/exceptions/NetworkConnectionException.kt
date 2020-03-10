package com.example.templateapp.data.datasource.remote.exceptions

class NetworkConnectionException :
    Exception() {
    override val message: String?
        get() = "Интернет недоступен"
    override val cause: Throwable?
        get() = Throwable("Проверьте соединение и\nповторите попытку")
}