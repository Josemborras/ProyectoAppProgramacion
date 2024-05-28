package com.example.trabajo_final_t3.data

import com.example.trabajo_final_t3.data.models.CardImage.CardImage
import com.example.trabajo_final_t3.data.models.Recipe.Recipe
import com.example.trabajo_final_t3.data.retrofit.RetrofitObjeto
import retrofit2.Response


class Repository {

    suspend fun recipesRandomAdd(number: Int) = RetrofitObjeto.retrofitInterface.rescipesRandom(number)

    suspend fun triviaRandomAdd() = RetrofitObjeto.retrofitInterface.triviaRandom()

    suspend fun recipeCard(id: Int) = RetrofitObjeto.retrofitInterface.recipeCard(id)

    suspend fun getRecipe(id: Int): Response<Recipe> {
        return RetrofitObjeto.retrofitInterface.obtenerRecipe(id)
    }
    suspend fun getCardImage(id: Int): Response<CardImage> {
        return RetrofitObjeto.retrofitInterface.obtenerCardImage(id)
    }
    suspend fun listRecipes(id: Int) = RetrofitObjeto.retrofitInterface.obtenerRecetasSimilares(id)

    suspend fun getInstructions(id: Int) = RetrofitObjeto.retrofitInterface.obtenerInstructions(id)

    suspend fun getIngredients(ingredientName: String) = RetrofitObjeto.retrofitInterface.getIngredients(ingredientName)

    suspend fun getRecipesByIngredients(ingredientsNames: String) = RetrofitObjeto.retrofitInterface.getRecipesByIngredients(ingredientsNames)

    suspend fun getRecipesByNutrients(minCarbs: Int, maxCarbs: Int, minProtein: Int, maxProtein: Int, minFat: Int, maxFat: Int, minCalories: Int, maxCalories: Int, number: Int) = RetrofitObjeto.retrofitInterface.getRecipesByNutrients(minCarbs, maxCarbs, minProtein, maxProtein, minFat, maxFat, minCalories, maxCalories, number)

}