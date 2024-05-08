package com.example.tabajo_finalt3.data.models


import com.google.gson.annotations.SerializedName

data class Measures(
    @SerializedName("metric")
    val metric: Metric?,
    @SerializedName("original")
    val original: Original?,
    @SerializedName("us")
    val us: Us?
)