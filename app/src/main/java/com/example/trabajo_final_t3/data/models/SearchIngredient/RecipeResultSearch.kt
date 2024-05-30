package com.example.trabajo_final_t3.data.models.SearchIngredient


import com.google.gson.annotations.SerializedName

data class RecipeResultSearch(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String
)