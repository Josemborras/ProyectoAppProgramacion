package com.example.tabajo_finalt3.data.models


import com.google.gson.annotations.SerializedName

data class Us(
    @SerializedName("amount")
    val amount: Double?,
    @SerializedName("unit")
    val unit: String?
)