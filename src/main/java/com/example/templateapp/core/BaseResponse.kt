package com.example.templateapp.core


open class BaseResponse<T : Any> {
    var success: Boolean? = null
    var data: T? = null
    var error: ErrorResponse? = null
    var base: String? = null
    var rates: LinkedHashMap<String, Double>? = null
}

data class ErrorResponse(
    var code: Int?,
    var info: String?,
    var type: String?
)
