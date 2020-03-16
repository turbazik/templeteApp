package com.example.templateapp.features.rates.domain.model

import com.example.templateapp.features.rates.data.datasource.remote.model.api.Rates

data class RatesEntity(
    var base: String?,
    var rates: List<Rates>?
)