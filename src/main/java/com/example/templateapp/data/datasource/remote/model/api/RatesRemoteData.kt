package com.example.templateapp.data.datasource.remote.model.api

data class RatesRemoteData(
    var base: String?,
    var date: String?,
    var rates: List<RatesRemoteDataItem>?,
    var success: Boolean?,
    var timestamp: Int?
)

data class RatesRemoteDataItem(
    var name: String?,
    var value: Double?
)