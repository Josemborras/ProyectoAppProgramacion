package com.example.trabajo_final_t3.data.models.shoppingList


import com.google.gson.annotations.SerializedName

data class Aisle(
    @SerializedName("aisle")
    val aisle: String?,
    @SerializedName("items")
    val items: List<Item>?
)