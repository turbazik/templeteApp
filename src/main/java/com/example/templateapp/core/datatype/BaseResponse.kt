package com.example.templateapp.core.datatype

open class BaseResponse<T : Any> {
    var success: Boolean? = null
    var Message: String? = null
    var Description: String? = null
    var Data: T? = null
}
