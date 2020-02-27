package com.example.templateapp.data.datasource.remote.model.response
import com.google.gson.annotations.SerializedName


data class RatesResponse(
    @SerializedName("base")
    var base: String?,
    @SerializedName("date")
    var date: String?,
    @SerializedName("rates")
    var rates: LinkedHashMap<String, Double>?,
    @SerializedName("success")
    var success: Boolean?,
    @SerializedName("timestamp")
    var timestamp: Int?
)