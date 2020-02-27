package com.example.templateapp.data.datasource.remote.mapper

import com.example.templateapp.core.BaseMapper
import com.example.templateapp.data.datasource.remote.model.api.Rates
import com.example.templateapp.data.datasource.remote.model.api.RatesData
import com.example.templateapp.data.datasource.remote.model.response.RatesResponse


object ResponseToApiMapper : BaseMapper<RatesResponse, RatesData> {
    override fun map(type: RatesResponse?): RatesData {
        return RatesData(
            base = type?.base,
            success = type?.success,
            date = type?.date,
            timestamp = type?.timestamp,
            rates = type?.rates?.map { Rates(name = it.key, value = it.value) } ?: listOf()
        )
    }
}
