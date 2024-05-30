package com.example.trabajo_final_t3.data.models.recipeRandom


import com.example.trabajo_final_t3.data.models.AllRecipeInfo.Recipe
import com.google.gson.annotations.SerializedName

data class RecipesRandom(
    @SerializedName("recipes")
    var recipes: ArrayList<Recipe>
)