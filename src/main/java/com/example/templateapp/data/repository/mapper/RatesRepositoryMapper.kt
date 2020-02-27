package com.example.templateapp.data.repository.mapper

import com.example.templateapp.core.BaseMapper
import com.example.templateapp.data.datasource.remote.model.api.RatesRemoteData
import com.example.templateapp.main.domain.model.RatesEntity
import com.example.templateapp.main.domain.model.RatesEntityItem

object ApiToEntityMapper : BaseMapper<RatesRemoteData, RatesEntity> {
    override fun map(type: RatesRemoteData?): RatesEntity {
        return RatesEntity(
            base = type?.base,
            success = type?.success,
            date = type?.date,
            timestamp = type?.timestamp,
            rates = type?.rates?.map { RatesEntityItem(name = it.name, value = it.value) }
                ?: listOf()
        )
    }
}
