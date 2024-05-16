package com.example.trabajo_finalt3.model.data.ListRecipe

data class RecipeItem(
    val id: Int,
    val imageType: String,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceUrl: String,
    val title: String
)