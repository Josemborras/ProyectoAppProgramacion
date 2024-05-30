package com.example.trabajo_final_t3.data.models.shoppingList


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("aisle")
    val aisle: String?,
    @SerializedName("cost")
    val cost: Double?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("ingredientId")
    val ingredientId: Int?,
    @SerializedName("measures")
    val measures: Measures?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("pantryItem")
    val pantryItem: Boolean?,
    @SerializedName("usageRecipeIds")
    val usageRecipeIds: List<Any>?,
    @SerializedName("usages")
    val usages: List<Any>?
)