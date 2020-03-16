package com.example.templateapp.features.rates.data.datasource.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.templateapp.features.rates.data.datasource.local.model.RatesLocalData

@Dao
interface RatesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveRates(rates: RatesLocalData)

    @Query("SELECT * FROM rates WHERE base = :base")
    suspend fun getRates(base: String): RatesLocalData
}