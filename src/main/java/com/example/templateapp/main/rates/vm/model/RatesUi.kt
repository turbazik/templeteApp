package com.example.templateapp.main.rates.vm.model

import com.example.templateapp.data.datasource.remote.model.api.Rates

data class RatesUi(
    var base: String?,
    var rates: List<Rates>?
)