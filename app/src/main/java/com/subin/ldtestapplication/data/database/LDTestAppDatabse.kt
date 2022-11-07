package com.subin.ldtestapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TvShowEntity::class], version = 1, exportSchema = false)
abstract class LDTestAppDatabase:RoomDatabase() {
    abstract fun provideDao():LDTestAppDao
}