package com.subin.ldtestapplication.data.repository

import androidx.lifecycle.MutableLiveData
import com.subin.ldtestapplication.data.database.LDTestAppDao
import com.subin.ldtestapplication.data.database.TvShowEntity
import com.subin.ldtestapplication.data.models.TVShowResponse
import com.subin.ldtestapplication.data.network.FetchTvShowApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class TVShowRepository @Inject constructor(private val api: FetchTvShowApi,
                                  private val dao: LDTestAppDao
):GetTvShowRepository {
    override fun getDataFromApi(liveData: MutableLiveData<TVShowResponse>) {

        val call: Call<TVShowResponse> =api.getTvShowFromAPI()
        call.enqueue(object: Callback<TVShowResponse> {
            override fun onResponse(
                call: Call<TVShowResponse>,
                response: Response<TVShowResponse>
            ) {
                liveData.postValue(response.body())
            }
            override fun onFailure(call: Call<TVShowResponse>, t: Throwable) {
                liveData.postValue(null)
            }
        })
    }

    override fun insertToDB(tvShowEntity: TvShowEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.insertArticleDetails(tvShowEntity)
        }
    }

    override fun getFromDataBase(liveListDB: MutableLiveData<List<TvShowEntity>>) {
        CoroutineScope(Dispatchers.IO).launch {
          liveListDB.postValue( dao.getDataDetails())
        }
    }

}