package com.example.trabajo_final_t3.data.retrofit

class Repository {
    private val retrofit = Retrofit.retrofitService

    suspend fun getIngredients(ingredientName: String) = retrofit.getIngredients(ingredientName)

    suspend fun getRecipes(ingredientsNames: String) = retrofit.getRecipes(ingredientsNames)
}