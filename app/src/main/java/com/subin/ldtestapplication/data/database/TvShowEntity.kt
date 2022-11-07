package com.subin.ldtestapplication.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "TvShowTable")
data class TvShowEntity(
    @ColumnInfo(name = "AirDate")
    var airdate: String?,
    @PrimaryKey
@ColumnInfo(name = "Id")
    var id: Int?,
    @ColumnInfo(name = "ImageUrl")
 val original: String?,
    @ColumnInfo(name = "ShowName")
    var name: String?,
    @ColumnInfo(name="EpisodeNumber")
    var number: Int?,
    @ColumnInfo(name="SeasonNumber")
    var season: Int?,
    @ColumnInfo(name="Summary")
    var summary: String?,

    ) {
}