package com.example.tabajo_finalt3.data.models


import com.google.gson.annotations.SerializedName

data class RecipeCard(
    @SerializedName("status")
    var status: String?,
    @SerializedName("time")
    var time: String?,
    @SerializedName("url")
    var url: String?
)