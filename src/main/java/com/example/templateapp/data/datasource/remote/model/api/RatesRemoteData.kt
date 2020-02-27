package com.example.templateapp.data.datasource.remote.model.api

import com.example.templateapp.data.datasource.remote.model.response.ErrorResponse

data class RatesRemoteData(
    var base: String?,
    var date: String?,
    var rates: List<Rates>?,
    var success: Boolean?,
    var timestamp: Int?,
    var error: ErrorResponse?

)

data class Rates(
    var name: String?,
    var value: Double?
)
