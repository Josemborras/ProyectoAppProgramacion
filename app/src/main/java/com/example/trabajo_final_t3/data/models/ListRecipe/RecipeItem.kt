package com.example.trabajo_final_t3.data.models.ListRecipe

data class RecipeItem(
    val id: Int,
    val imageType: String,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceUrl: String,
    val title: String
)