package com.example.templateapp.data.datasource.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.templateapp.data.datasource.local.converters.RatesConverter

const val TABLE_NAME = "rates"

@Entity(tableName = TABLE_NAME)
@TypeConverters(RatesConverter::class)
data class RatesLocalData(
    @PrimaryKey
    var base: String,
    var rates: List<RatesLocal>?

)

data class RatesLocal(
    var name: String?,
    var value: Double?
)
