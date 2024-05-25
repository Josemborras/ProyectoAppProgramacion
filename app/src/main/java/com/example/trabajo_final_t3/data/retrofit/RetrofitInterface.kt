package com.example.trabajo_final_t3.data.retrofit

import com.example.trabajo_final_t3.data.models.ingredients.IngredientsResponse
import com.example.trabajo_final_t3.data.models.recipes.RecipeResponse
import com.example.trabajo_final_t3.data.models.recipesbynutrients.RecipesByNutrientsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitInterface {

    companion object {
        const val API_KEY = "e6babe78c3164d15a137f94b95196593"
    }

    /*
     * getIngredients es la petición para buscar ingredientes según
     * lo que escriba el usuario en el buscador (se le pasa el string
     * al método como query)
     *
     * getRecipes es la petición para buscar recetas según
     * los ingredientes que haya buscado el usuario, se guardan los
     * ingredientes y luego se formatean los nombres de cada uno como
     * "ingrediente1,ingrediente2,ingrediente3" y un número de recetas
     * máximo para que devuelva la api (1-100, o 10 por defecto)
     *
     *  */
    @Headers(
        "Content-Type: application/json",
        "x-api-key: $API_KEY"
    )
    @GET("food/ingredients/search")
    suspend fun getIngredients(
        @Query("query") ingredientName: String
    ): Response<IngredientsResponse>

    @Headers(
        "Content-Type: application/json",
        "x-api-key: $API_KEY"
    )
    @GET("recipes/findByIngredients")
    suspend fun getRecipesByIngredients(
        @Query("ingredients") ingredientes: String
    ): Response<RecipeResponse>

    @Headers(
        "Content-Type: application/json",
        "x-api-key: $API_KEY"
    )
    @GET("recipes/findByNutrients")
    suspend fun getRecipesByNutrients(
        @Query("minCarbs") minCarbs: Int,
        @Query("maxCarbs") maxCarbs: Int,
        @Query("minProtein") minProtein: Int,
        @Query("maxProtein") maxProtein: Int,
        @Query("minFat") minFat: Int,
        @Query("maxFat") maxFat: Int,
        @Query("minCalories") minCalories: Int,
        @Query("maxCalories") maxCalories: Int,
        @Query("number") number: Int,
    ): Response<RecipesByNutrientsResponse>
}