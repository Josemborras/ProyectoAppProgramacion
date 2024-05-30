package com.example.trabajo_final_t3.data.models.recipeRandom


import com.example.trabajo_final_t3.data.models.StepsRecipe.Step
import com.google.gson.annotations.SerializedName

data class AnalyzedInstruction(
    @SerializedName("name")
    var name: String?,
    @SerializedName("steps")
    var steps: List<Step?>?
)