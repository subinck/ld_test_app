package com.subin.ldtestapplication.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.subin.ldtestapplication.data.models.TVShowResponseItem


@Dao
interface LDTestAppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticleDetails(item:TvShowEntity):Long?

    @Query("SELECT * FROM TvShowTable" )
    suspend fun getDataDetails(): List<TvShowEntity>

//    @Query("DELETE FROM TvShowTable WHERE Id = :id")
//    suspend fun  deleteDetailsById(id:String)

}
