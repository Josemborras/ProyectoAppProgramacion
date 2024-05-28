package com.example.trabajo_final_t3.data.models.Recipe


import com.google.gson.annotations.SerializedName

data class Measures(
    @SerializedName("metric")
    var metric: Metric?,
    @SerializedName("us")
    var us: Us?
)