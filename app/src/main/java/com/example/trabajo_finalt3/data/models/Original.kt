package com.example.trabajo_finalt3.data.models


import com.google.gson.annotations.SerializedName

data class Original(
    @SerializedName("amount")
    val amount: Double?,
    @SerializedName("unit")
    val unit: String?
)