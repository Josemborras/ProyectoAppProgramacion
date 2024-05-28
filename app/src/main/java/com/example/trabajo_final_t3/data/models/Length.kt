package com.example.trabajo_final_t3.data.models


import com.google.gson.annotations.SerializedName

data class Length(
    @SerializedName("number")
    var number: Int?,
    @SerializedName("unit")
    var unit: String?
)