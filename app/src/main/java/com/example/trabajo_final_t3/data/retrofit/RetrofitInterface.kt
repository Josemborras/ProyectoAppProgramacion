package com.example.trabajo_final_t3.data.retrofit

import com.example.trabajo_final_t3.data.models.CardImage.CardImage
import com.example.trabajo_final_t3.data.models.ListRecipe.RecipeItem
import com.example.trabajo_final_t3.data.models.Recipe.Recipe
import com.example.trabajo_final_t3.data.models.RecipeCard
import com.example.trabajo_final_t3.data.models.RecipesRandom
import com.example.trabajo_final_t3.data.models.Steps.StepsResponse
import com.example.trabajo_final_t3.data.models.TriviaRandom
import com.example.trabajo_final_t3.data.models.ingredients.IngredientsResponse
import com.example.trabajo_final_t3.data.models.recipes.RecipeResponse
import com.example.trabajo_final_t3.data.models.recipesbynutrients.RecipesByNutrientsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitInterface {


    companion object {
        const val API_KEY_DANI = "e6babe78c3164d15a137f94b95196593"
    }
    @Headers(
        "Content-Type: application/json",
        "x-api-key: 8bd01298d80e4b8f90b5985392f9fb1b"
    )

    @GET("recipes/random")
    suspend fun rescipesRandom(
        @Query("number") number: Int
    ): Response<RecipesRandom>

    @Headers(
        "Content-Type: application/json",
        "x-api-key: 8bd01298d80e4b8f90b5985392f9fb1b"
    )

    @GET("food/trivia/random")
    suspend fun triviaRandom(): Response<TriviaRandom>



    @Headers(
        "Content-Type: application/json",
        "x-api-key: 8bd01298d80e4b8f90b5985392f9fb1b"
    )

    @GET("recipes/{id}/card")
    suspend fun recipeCard(
        @Path("id") id: Int
    ):Response<RecipeCard>

    @Headers("x-api-key: 6ae3b4a55ffc4189ba718e387513a9f2","Content-Type: application/json")
    @GET("recipes/{id}/information")
    suspend fun obtenerRecipe(@Path("id") id: Int): Response<Recipe>

    @Headers("x-api-key: 6ae3b4a55ffc4189ba718e387513a9f2","Content-Type: application/json")
    @GET("recipes/{id}/similar")
    suspend fun obtenerRecetasSimilares(@Path("id") id: Int): Response<ArrayList<RecipeItem>>

    @Headers("x-api-key: 6ae3b4a55ffc4189ba718e387513a9f2","Content-Type: application/json")
    @GET("recipes/{id}/card")
    suspend fun obtenerCardImage(@Path("id") id: Int): Response<CardImage>

    @Headers("x-api-key: 6ae3b4a55ffc4189ba718e387513a9f2","Content-Type: application/json")
    @GET("recipes/{id}/analyzedInstructions")
    suspend fun obtenerInstructions(@Path("id") id: Int): Response<StepsResponse>

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
        "x-api-key: $API_KEY_DANI"
    )
    @GET("food/ingredients/search")
    suspend fun getIngredients(
        @Query("query") ingredientName: String
    ): Response<IngredientsResponse>

    @Headers(
        "Content-Type: application/json",
        "x-api-key: $API_KEY_DANI"
    )
    @GET("recipes/findByIngredients")
    suspend fun getRecipesByIngredients(
        @Query("ingredients") ingredientes: String
    ): Response<RecipeResponse>

    @Headers(
        "Content-Type: application/json",
        "x-api-key: $API_KEY_DANI"
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