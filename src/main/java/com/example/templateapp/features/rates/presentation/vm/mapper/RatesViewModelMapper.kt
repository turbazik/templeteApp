package com.example.templateapp.features.rates.presentation.vm.mapper

import com.example.templateapp.core.BaseMapper
import com.example.templateapp.core.BaseResponse
import com.example.templateapp.features.rates.data.datasource.local.model.RatesLocal
import com.example.templateapp.features.rates.data.datasource.local.model.RatesLocalData
import com.example.templateapp.features.rates.data.datasource.remote.model.api.Rates
import com.example.templateapp.features.rates.data.datasource.remote.model.api.RatesRemoteData
import com.example.templateapp.features.rates.domain.model.RatesEntity
import com.example.templateapp.features.rates.presentation.vm.model.RatesUi

object RatesEntityToUiMapper : BaseMapper<RatesEntity, RatesUi> {
    override fun map(type: RatesEntity?): RatesUi {
        return RatesUi(
            base = type?.base,
            rates = type?.rates
        )
    }
}
