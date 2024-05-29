package com.example.trabajo_final_t3.data.models.shoppingList


import com.google.gson.annotations.SerializedName

data class Original(
    @SerializedName("amount")
    val amount: Double?,
    @SerializedName("unit")
    val unit: String?
)