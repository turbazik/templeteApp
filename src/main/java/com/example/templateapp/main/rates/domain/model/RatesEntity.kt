package com.example.templateapp.main.rates.domain.model

data class RatesEntity(
    var base: String?,
    var date: String?,
    var rates: List<Rates>?,
    var success: Boolean?,
    var timestamp: Int?
)

data class Rates(
    var name: String?,
    var value: Double?
)