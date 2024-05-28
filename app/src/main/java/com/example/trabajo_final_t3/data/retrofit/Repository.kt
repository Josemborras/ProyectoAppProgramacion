package com.example.trabajo_final_t3.data.retrofit

class Repository {
    private val retrofit = Retrofit.retrofitService

    suspend fun getIngredients(ingredientName: String) = retrofit.getIngredients(ingredientName)

    suspend fun getRecipesByIngredients(ingredientsNames: String) = retrofit.getRecipesByIngredients(ingredientsNames)

    suspend fun getRecipesByNutrients(minCarbs: Int, maxCarbs: Int, minProtein: Int, maxProtein: Int, minFat: Int, maxFat: Int, minCalories: Int, maxCalories: Int, number: Int) = retrofit.getRecipesByNutrients(minCarbs, maxCarbs, minProtein, maxProtein, minFat, maxFat, minCalories, maxCalories, number)

}