package com.example.tabajo_finalt3.data.models


import com.google.gson.annotations.SerializedName

data class Metric(
    @SerializedName("amount")
    var amount: Double?,
    @SerializedName("unitLong")
    var unitLong: String?,
    @SerializedName("unitShort")
    var unitShort: String?
)