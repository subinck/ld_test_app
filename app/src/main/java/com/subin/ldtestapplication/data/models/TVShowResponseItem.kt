package com.subin.ldtestapplication.data.models


import com.google.gson.annotations.SerializedName

data class TVShowResponseItem(
    @SerializedName("airdate")
    var airdate: String?,
    @SerializedName("airstamp")
    var airstamp: String?,
    @SerializedName("airtime")
    var airtime: String?,
    @SerializedName("id")
    var id: Int?,

    @SerializedName("image")

    var image: Image,
    @SerializedName("_links")
    var links: Links?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("number")
    var number: Int?,
    @SerializedName("rating")
    var rating: Rating?,
    @SerializedName("runtime")
    var runtime: Int?,
    @SerializedName("season")
    var season: Int?,
    @SerializedName("summary")
    var summary: String?,
    @SerializedName("type")
    var type: String?,
    @SerializedName("url")
    var url: String?
)