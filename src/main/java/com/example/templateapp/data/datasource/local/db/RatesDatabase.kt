package com.example.templateapp.data.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.templateapp.data.datasource.local.model.RatesLocalData
import com.example.templateapp.data.datasource.local.room.RatesDao

@Database(entities = [RatesLocalData::class], version = 1)
abstract class RatesDatabase : RoomDatabase() {
    abstract fun ratesDao(): RatesDao
}