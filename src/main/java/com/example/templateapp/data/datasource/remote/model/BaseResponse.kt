package com.example.templateapp.data.datasource.remote.model

open class BaseResponse<T : Any> {
    var success: Boolean? = null
    var Message: String? = null
    var Description: String? = null
    var Data: T? = null
}
