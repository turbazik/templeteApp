package com.example.templateapp.data.repository.mapper

import com.example.templateapp.core.BaseMapper
import com.example.templateapp.data.datasource.remote.model.api.Rates
import com.example.templateapp.data.datasource.remote.model.api.RatesRemoteData
import com.example.templateapp.main.rates.domain.model.RatesEntity

object ApiToEntityMapper : BaseMapper<RatesRemoteData, RatesEntity> {
    override fun map(type: RatesRemoteData?): RatesEntity {
        return RatesEntity(
            base = type?.base,
            success = type?.success,
            date = type?.date,
            timestamp = type?.timestamp,
            rates = type?.rates?.map {
                Rates(
                    name = it.name,
                    value = it.value
                )
            }
                ?: listOf(),
            error = type?.error
        )
    }
}
