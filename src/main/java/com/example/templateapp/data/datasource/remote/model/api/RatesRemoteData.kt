package com.example.templateapp.data.datasource.remote.model.api

data class RatesRemoteData(
    var base: String?,
    var date: String?,
    var rates: List<Rates>?,
    var timestamp: Int?

)

data class Rates(
    var name: String?,
    var value: Double?
)
