package com.example.templateapp.main.domain.model

data class RatesEntity(
    var base: String?,
    var date: String?,
    var rates: List<RatesEntityItem>?,
    var success: Boolean?,
    var timestamp: Int?
)

data class RatesEntityItem(
    var name: String?,
    var value: Double?
)