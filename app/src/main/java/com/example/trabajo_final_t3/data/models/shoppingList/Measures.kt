package com.example.trabajo_final_t3.data.models.shoppingList


import com.google.gson.annotations.SerializedName

data class Measures(
    @SerializedName("metric")
    val metric: Metric?,
    @SerializedName("original")
    val original: Original?,
    @SerializedName("us")
    val us: Us?
)