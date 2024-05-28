package com.example.trabajo_finalt3.data.models


import com.google.gson.annotations.SerializedName

data class Aisle(
    @SerializedName("aisle")
    val aisle: String?,
    @SerializedName("items")
    val items: List<Item>?
)