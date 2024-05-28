package com.example.trabajo_final_t3.data.models


import com.google.gson.annotations.SerializedName

data class Us(
    @SerializedName("amount")
    var amount: Double?,
    @SerializedName("unitLong")
    var unitLong: String?,
    @SerializedName("unitShort")
    var unitShort: String?
)