package com.example.templateapp.data.datasource.remote.mapper

import com.example.templateapp.core.BaseMapper
import com.example.templateapp.core.BaseResponse
import com.example.templateapp.data.datasource.remote.model.api.Rates
import com.example.templateapp.data.datasource.remote.model.api.RatesRemoteData
import com.example.templateapp.data.datasource.remote.model.response.RatesResponse


object ResponseToApiMapper :
    BaseMapper<BaseResponse<RatesResponse>, BaseResponse<RatesRemoteData>> {
    override fun map(type: BaseResponse<RatesResponse>?): BaseResponse<RatesRemoteData> {
        val response = BaseResponse<RatesRemoteData>()
        response.success = type?.success
        response.error = type?.error
        response.data = RatesRemoteData(
            base = type?.data?.base,
            rates = type?.data?.rates?.map {
                Rates(
                    name = it.key,
                    value = it.value
                )
            } ?: listOf()
        )
        return response
    }
}
