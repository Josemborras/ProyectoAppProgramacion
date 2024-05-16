package com.example.tabajo_finalt3.data.models


import com.google.gson.annotations.SerializedName

data class Temperature(
    @SerializedName("number")
    var number: Double?,
    @SerializedName("unit")
    var unit: String?
)