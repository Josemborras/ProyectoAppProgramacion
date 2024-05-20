package com.example.trabajo_final_t3.data.models.ingredients


import com.google.gson.annotations.SerializedName

data class IngredientsResponse(
    @SerializedName("number")
    val number: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("results")
    val results: List<Resultado>,
    @SerializedName("totalResults")
    val totalResults: Int
)