package com.subin.ldtestapplication.data.network

import com.subin.ldtestapplication.data.models.TVShowResponse
import retrofit2.Call
import retrofit2.http.GET

interface FetchTvShowApi {

    @GET("episodes")
    fun getTvShowFromAPI(): Call<TVShowResponse>

}