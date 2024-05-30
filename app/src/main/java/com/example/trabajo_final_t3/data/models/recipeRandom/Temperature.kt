package com.example.trabajo_final_t3.data.models.recipeRandom


import com.google.gson.annotations.SerializedName

data class Temperature(
    @SerializedName("number")
    var number: Double?,
    @SerializedName("unit")
    var unit: String?
)