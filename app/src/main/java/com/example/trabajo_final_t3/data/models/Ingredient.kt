package com.example.trabajo_final_t3.data.models


import com.google.gson.annotations.SerializedName

data class Ingredient(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("image")
    var image: String?,
    @SerializedName("localizedName")
    var localizedName: String?,
    @SerializedName("name")
    var name: String?
)