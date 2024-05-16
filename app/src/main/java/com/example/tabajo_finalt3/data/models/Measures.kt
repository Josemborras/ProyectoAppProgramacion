package com.example.tabajo_finalt3.data.models


import com.google.gson.annotations.SerializedName

data class Measures(
    @SerializedName("metric")
    var metric: Metric?,
    @SerializedName("us")
    var us: Us?
)