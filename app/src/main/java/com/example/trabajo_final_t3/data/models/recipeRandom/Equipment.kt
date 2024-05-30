package com.example.trabajo_final_t3.data.models.recipeRandom


import com.google.gson.annotations.SerializedName

data class Equipment(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("image")
    var image: String?,
    @SerializedName("localizedName")
    var localizedName: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("temperature")
    var temperature: Temperature?
)