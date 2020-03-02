package com.example.templateapp.data.datasource.remote.model.api

data class RatesRemoteData(
    var base: String?,
    var rates: List<Rates>?

)

data class Rates(
    var name: String?,
    var value: Double?
)
