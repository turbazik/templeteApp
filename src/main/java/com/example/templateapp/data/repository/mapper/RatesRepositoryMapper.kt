package com.example.templateapp.data.repository.mapper

import com.example.templateapp.core.BaseMapper
import com.example.templateapp.core.BaseResponse
import com.example.templateapp.data.datasource.local.model.RatesLocal
import com.example.templateapp.data.datasource.local.model.RatesLocalData
import com.example.templateapp.data.datasource.remote.model.api.Rates
import com.example.templateapp.data.datasource.remote.model.api.RatesRemoteData
import com.example.templateapp.main.rates.domain.model.RatesEntity

object ApiToEntityMapper : BaseMapper<BaseResponse<RatesRemoteData>, BaseResponse<RatesEntity>> {
    override fun map(type: BaseResponse<RatesRemoteData>?): BaseResponse<RatesEntity> {
        val response = BaseResponse<RatesEntity>()
        response.success = type?.success!!
        response.error = type.error
        response.data = RatesEntity(
            base = type.data?.base,
            rates = type.data?.rates
        )
        return response
    }
}

object ApiToLocalMapper : BaseMapper<RatesRemoteData, RatesLocalData> {
    override fun map(type: RatesRemoteData?): RatesLocalData {
        return RatesLocalData(
            base = type?.base!!,
            rates = type.rates?.map {
                RatesLocal(
                    name = it.name,
                    value = it.value
                )
            } ?: listOf()
        )
    }
}

object LocalToEntityMapper : BaseMapper<RatesLocalData?, BaseResponse<RatesEntity>?> {
    override fun map(type: RatesLocalData?): BaseResponse<RatesEntity>? {
        if (type == null)
            return null
        val response = BaseResponse<RatesEntity>()
        response.success = true
        response.error = null
        response.data = RatesEntity(
            base = type.base,
            rates = type.rates?.map {
                Rates(
                    name = it.name,
                    value = it.value
                )
            } ?: listOf()
        )
        return response
    }
}

