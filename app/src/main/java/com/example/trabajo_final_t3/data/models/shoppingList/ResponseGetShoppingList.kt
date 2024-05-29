package com.example.trabajo_final_t3.data.models.shoppingList


import com.google.gson.annotations.SerializedName

data class ResponseGetShoppingList(
    @SerializedName("aisles")
    val aisles: List<Aisle>?,
    @SerializedName("cost")
    val cost: Double?,
    @SerializedName("endDate")
    val endDate: Int?,
    @SerializedName("startDate")
    val startDate: Int?
)