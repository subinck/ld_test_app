package com.subin.ldtestapplication.data.models


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("self")
    var self: Self
)