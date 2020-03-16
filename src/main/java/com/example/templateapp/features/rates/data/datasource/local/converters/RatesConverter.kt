package com.example.templateapp.features.rates.data.datasource.local.converters

import androidx.room.TypeConverter
import com.example.templateapp.features.rates.data.datasource.local.model.RatesLocal
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RatesConverter {

    @TypeConverter
    fun fromRatesList(value: List<RatesLocal>): String {
        val gson = Gson()
        val type = object : TypeToken<List<RatesLocal>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toRatesList(value: String): List<RatesLocal> {
        val gson = Gson()
        val type = object : TypeToken<List<RatesLocal>>() {}.type
        return gson.fromJson(value, type)
    }
}