package com.subin.ldtestapplication.data.models


import com.google.gson.annotations.SerializedName

data class Rating(
    @SerializedName("average")
    var average: Double?
)