package com.example.trabajo_final_t3.data.models.shoppingList


import com.google.gson.annotations.SerializedName

data class ResponseDeleteItem(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: String?
)