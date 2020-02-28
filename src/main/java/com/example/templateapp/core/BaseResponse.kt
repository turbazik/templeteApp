package com.example.templateapp.core


open class BaseResponse<T : Any> {
    var success: Boolean? = null
    var data: T? = null
    var error: ErrorResponse? = null
}

data class ErrorResponse(
    var code: Int?,
    var info: String?,
    var type: String?
)
