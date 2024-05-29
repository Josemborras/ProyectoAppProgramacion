package com.example.trabajo_final_t3.data.models.SearchRecipesByIngredients


import com.google.gson.annotations.SerializedName

data class RecipesResponseItem(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("imageType")
    val imageType: String?,
    @SerializedName("likes")
    val likes: Int?,
    @SerializedName("missedIngredientCount")
    val missedIngredientCount: Int?,
    @SerializedName("missedIngredients")
    val missedIngredients: List<MissedIngredient?>?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("unusedIngredients")
    val unusedIngredients: List<Any?>?,
    @SerializedName("usedIngredientCount")
    val usedIngredientCount: Int?,
    @SerializedName("usedIngredients")
    val usedIngredients: List<UsedIngredient?>?,

    @SerializedName("calories")
    val calories: Int?,
    @SerializedName("carbs")
    val carbs: String?,
    @SerializedName("fat")
    val fat: String?,
    @SerializedName("protein")
    val protein: String?
)