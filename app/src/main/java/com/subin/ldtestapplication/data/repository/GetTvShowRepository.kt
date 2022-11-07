package com.subin.ldtestapplication.data.repository

import androidx.lifecycle.MutableLiveData
import com.subin.ldtestapplication.data.database.TvShowEntity
import com.subin.ldtestapplication.data.models.TVShowResponse

interface GetTvShowRepository {

    fun getDataFromApi(liveData: MutableLiveData<TVShowResponse>)
     fun insertToDB(tvShowEntity: TvShowEntity)
    fun getFromDataBase(liveListDB: MutableLiveData<List<TvShowEntity>>)


}