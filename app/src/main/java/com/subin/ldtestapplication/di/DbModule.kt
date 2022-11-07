package com.subin.ldtestapplication.di

import android.content.Context
import androidx.room.Room
import com.subin.ldtestapplication.data.database.LDTestAppDao
import com.subin.ldtestapplication.data.database.LDTestAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {
@Provides
@Singleton
fun provideDb(@ApplicationContext app:Context)=Room.databaseBuilder(
    app,
    LDTestAppDatabase::class.java,
    "LDTestAppDB"
).build()

    @Provides
    @Singleton
    fun providesDao(database: LDTestAppDatabase):LDTestAppDao{
        return database.provideDao()
    }
}