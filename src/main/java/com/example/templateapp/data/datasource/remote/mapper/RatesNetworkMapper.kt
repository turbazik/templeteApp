package com.example.templateapp.data.datasource.remote.mapper

import com.example.templateapp.core.BaseMapper
import com.example.templateapp.data.datasource.remote.model.api.RatesRemoteData
import com.example.templateapp.data.datasource.remote.model.api.RatesRemoteDataItem
import com.example.templateapp.data.datasource.remote.model.response.RatesResponse


object ResponseToApiMapper : BaseMapper<RatesResponse, RatesRemoteData> {
    override fun map(type: RatesResponse?): RatesRemoteData {
        return RatesRemoteData(
            base = type?.base,
            success = type?.success,
            date = type?.date,
            timestamp = type?.timestamp,
            rates = type?.rates?.map {
                RatesRemoteDataItem(
                    name = it.key,
                    value = it.value
                )
            } ?: listOf()
        )
    }
}
