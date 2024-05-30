package com.example.trabajo_final_t3.data.models.shoppingList


import com.google.gson.annotations.SerializedName

data class PostItem(
    @SerializedName("aisle")
    val aisle: String?,
    @SerializedName("item")
    val item: String?,
    @SerializedName("parse")
    val parse: Boolean?
)