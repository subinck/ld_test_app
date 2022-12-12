package com.subin.ldtestapplication.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.subin.ldtestapplication.data.database.TvShowEntity
import com.subin.ldtestapplication.data.models.TVShowResponse
import com.subin.ldtestapplication.data.models.TVShowResponseItem
import com.subin.ldtestapplication.data.repository.TVShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel  @Inject constructor(
    private  val repository: TVShowRepository
):ViewModel() {
    private var liveTvShowList: MutableLiveData<TVShowResponse> = MutableLiveData()
    private var liveListDB:MutableLiveData<List<TvShowEntity>> = MutableLiveData()


    fun getLiveDataObserver(): MutableLiveData<TVShowResponse> {
        return liveTvShowList
    }


    fun loadListOfData() {
        repository.getDataFromApi(liveTvShowList)
        repository.getFromDataBase(liveListDB)
    }

    fun getFromDB():MutableLiveData<List<TvShowEntity>>{
        return liveListDB
    }

    fun insertData(article: List<TVShowResponseItem>){

        for (item in article) {
            val airDate = item.airdate
            val id = item.id
           val original = item.image?.original
            val name = item.name
            val number = item.number
            val season = item.season
            val summary = item.summary
            val tvShowEntity= TvShowEntity(airDate,id,original,name,number,season,summary)
            repository.insertToDB(tvShowEntity)
        }
      }


}