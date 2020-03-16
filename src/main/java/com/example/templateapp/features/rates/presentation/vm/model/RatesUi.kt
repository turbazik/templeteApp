package com.example.templateapp.features.rates.presentation.vm.model

import com.example.templateapp.features.rates.data.datasource.remote.model.api.Rates

data class RatesUi(
    var base: String?,
    var rates: List<Rates>?
)