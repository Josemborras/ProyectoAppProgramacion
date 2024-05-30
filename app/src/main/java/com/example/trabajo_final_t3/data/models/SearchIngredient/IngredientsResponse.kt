package com.example.trabajo_final_t3.data.models.SearchIngredient


import com.google.gson.annotations.SerializedName

data class IngredientsResponse(
    @SerializedName("number")
    val number: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("results")
    val results: List<RecipeResultSearch>,
    @SerializedName("totalResults")
    val totalResults: Int
)