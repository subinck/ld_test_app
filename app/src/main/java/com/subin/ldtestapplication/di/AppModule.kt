package com.subin.ldtestapplication.di

import com.subin.ldtestapplication.Utils.Constants
import com.subin.ldtestapplication.data.network.FetchTvShowApi
import com.subin.ldtestapplication.data.repository.GetTvShowRepository
import com.subin.ldtestapplication.data.repository.TVShowRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton



    @Module
    @InstallIn(SingletonComponent::class)
    object AppModule {

        @Singleton
        @Provides
        fun getAPI(retrofit: Retrofit): FetchTvShowApi {
            return retrofit.create(FetchTvShowApi::class.java)
        }

        @Singleton
        @Provides
        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Module
        @InstallIn(SingletonComponent::class)
        interface AppModuleInt {

            @Binds
            @Singleton
            fun providesMainActivityRepository(repository: TVShowRepository): GetTvShowRepository
        }
    }
