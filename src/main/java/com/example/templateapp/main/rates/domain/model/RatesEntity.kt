package com.example.templateapp.main.rates.domain.model

import com.example.templateapp.data.datasource.remote.model.api.Rates

data class RatesEntity(
    var base: String?,
    var date: String?,
    var rates: List<Rates>?,
    var timestamp: Int?
)