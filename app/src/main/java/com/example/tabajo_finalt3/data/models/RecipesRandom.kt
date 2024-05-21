package com.example.tabajo_finalt3.data.models


import com.google.gson.annotations.SerializedName

data class RecipesRandom(
    @SerializedName("recipes")
    var recipes: ArrayList<Recipe>
)