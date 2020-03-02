package com.example.templateapp.data.datasource.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.templateapp.data.datasource.local.model.RatesLocalData

@Dao
interface RatesDao {

    @Insert
    fun saveRates(rates: List<RatesLocalData>)

    @Query("SELECT * FROM rates WHERE base = :base")
    suspend fun getRates(base: String): RatesLocalData
}