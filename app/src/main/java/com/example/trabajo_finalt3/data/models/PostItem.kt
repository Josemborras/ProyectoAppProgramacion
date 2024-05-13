package com.example.trabajo_finalt3.data.models


import com.google.gson.annotations.SerializedName

data class PostItem(
    @SerializedName("aisle")
    val aisle: String?,
    @SerializedName("item")
    val item: String?,
    @SerializedName("parse")
    val parse: Boolean?
)