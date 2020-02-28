package com.example.templateapp.data.repository.mapper

import com.example.templateapp.core.BaseMapper
import com.example.templateapp.core.BaseResponse
import com.example.templateapp.data.datasource.remote.model.api.Rates
import com.example.templateapp.data.datasource.remote.model.api.RatesRemoteData
import com.example.templateapp.main.rates.domain.model.RatesEntity

object ApiToEntityMapper : BaseMapper<BaseResponse<RatesRemoteData>, BaseResponse<RatesEntity>> {
    override fun map(type: BaseResponse<RatesRemoteData>?): BaseResponse<RatesEntity> {
        val response = BaseResponse<RatesEntity>()
        response.success = type?.success
        response.data = RatesEntity(
            base = type?.data?.base,
            date = type?.data?.date,
            timestamp = type?.data?.timestamp,
            rates = type?.data?.rates?.map {
                Rates(
                    name = it.name,
                    value = it.value
                )
            } ?: listOf()
        )
        return response
    }
}
