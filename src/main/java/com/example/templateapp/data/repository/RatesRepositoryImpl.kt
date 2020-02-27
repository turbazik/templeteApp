package com.example.templateapp.data.repository

import com.example.templateapp.core.BaseRepository
import com.example.templateapp.main.rates.domain.RatesRepository
import java.util.*

class RatesRepositoryImpl(

) : BaseRepository(), RatesRepository {

    override suspend fun getRates(currency: String): Result<RatesEntity>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}