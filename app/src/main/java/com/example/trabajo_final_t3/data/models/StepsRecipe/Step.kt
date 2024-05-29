package com.example.trabajo_final_t3.data.models.StepsRecipe


import com.example.trabajo_final_t3.data.models.recipeRandom.Equipment
import com.example.trabajo_final_t3.data.models.recipeRandom.Length
import com.google.gson.annotations.SerializedName

data class Step(
    @SerializedName("equipment")
    var equipment: List<Equipment?>?,
    @SerializedName("ingredients")
    var ingredients: List<Ingredient?>?,
    @SerializedName("length")
    var length: Length?,
    @SerializedName("number")
    var number: Int?,
    @SerializedName("step")
    var step: String?
)