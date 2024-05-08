package com.example.tabajo_finalt3.data.models


import com.google.gson.annotations.SerializedName

data class ResponseDeleteItem(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: String?
)