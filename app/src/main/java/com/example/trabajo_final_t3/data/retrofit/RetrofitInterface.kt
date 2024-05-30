package com.example.trabajo_final_t3.data.retrofit

import com.example.trabajo_final_t3.data.models.AllRecipeInfo.Recipe
import com.example.trabajo_final_t3.data.models.SearchIngredient.IngredientsResponse
import com.example.trabajo_final_t3.data.models.SearchRecipesByIngredients.ListRecipeResponse
import com.example.trabajo_final_t3.data.models.SearchRecipesByIngredients.RecipesResponseItem
import com.example.trabajo_final_t3.data.models.StepsRecipe.StepsResponse
import com.example.trabajo_final_t3.data.models.recipeCard.RecipeCard
import com.example.trabajo_final_t3.data.models.recipeRandom.RecipesRandom
import com.example.trabajo_final_t3.data.models.shoppingList.Item
import com.example.trabajo_final_t3.data.models.shoppingList.PostItem
import com.example.trabajo_final_t3.data.models.shoppingList.ResponseDeleteItem
import com.example.trabajo_final_t3.data.models.shoppingList.ResponseGetShoppingList
import com.example.trabajo_final_t3.data.models.triviaRandom.TriviaRandom
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitInterface {

    companion object {
        const val API_KEY_DANI = "e6babe78c3164d15a137f94b95196593"
        const val API_KEY_DANI_DOS = "6f08c8334d954a858362da7bf0140500"
        const val API_KEY_JOSE = "69b49e24974a4fd88b8e01f793a4e6e6"
        const val API_KEY_JOSE_DOS = "5e5385644ccb4f4ca7e6079366cc4f7b"
        const val API_KEY_JOSE_TRES = "7509ee3ea4c44ff08dd7ff26ff8a22fc"
        const val API_KEY_SANDRA = "90b3b4e1f1e64864943fe169af738877"
        const val API_KEY_SANDRA_DOS = "0a3d1774dec74565a4565e2877d195ac"
        const val API_KEY_MARIA = "8bd01298d80e4b8f90b5985392f9fb1b"
    }

    /**
     * @author Maria Belen
     * peticiones get con sus correspondientes headers
     */
    @Headers(
        "Content-Type: application/json",
        "x-api-key: $API_KEY_MARIA"
    )
    @GET("recipes/random")
    suspend fun rescipesRandom(
        @Query("number") number: Int
    ): Response<RecipesRandom>

    @Headers(
        "Content-Type: application/json",
        "x-api-key: $API_KEY_MARIA"
    )
    @GET("food/trivia/random")
    suspend fun triviaRandom(): Response<TriviaRandom>


    @Headers(
        "Content-Type: application/json",
        "x-api-key: $API_KEY_MARIA"
    )
    @GET("recipes/{id}/card")
    suspend fun recipeCard(
        @Path("id") id: Int
    ):Response<RecipeCard>

    /**
     * @author Jose Borrás
     * peticiones get con sus correspondientes headers
     */

    @Headers("x-api-key: $API_KEY_JOSE","Content-Type: application/json")
    @GET("recipes/{id}/information")
    suspend fun obtenerRecipe(@Path("id") id: Int): Response<Recipe>

    @Headers("x-api-key: $API_KEY_JOSE_DOS","Content-Type: application/json")
    @GET("recipes/{id}/similar")
    suspend fun obtenerRecetasSimilares(@Path("id") id: Int): Response<ArrayList<RecipesResponseItem>>

    @Headers("x-api-key: $API_KEY_JOSE_TRES","Content-Type: application/json")
    @GET("recipes/{id}/card")
    suspend fun obtenerCardImage(@Path("id") id: Int): Response<RecipeCard>

    @Headers("x-api-key: $API_KEY_JOSE_TRES","Content-Type: application/json")
    @GET("recipes/{id}/analyzedInstructions")
    suspend fun obtenerInstructions(@Path("id") id: Int): Response<StepsResponse>

    /**
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
     * @author Daniel
     * peticiones get con sus correspondientes headers
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
        "x-api-key: $API_KEY_DANI_DOS"
    )
    @GET("recipes/findByIngredients")
    suspend fun getRecipesByIngredients(
        @Query("ingredients") ingredientes: String
    ): Response<ListRecipeResponse>

    @Headers(
        "Content-Type: application/json",
        "x-api-key: $API_KEY_DANI_DOS"
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
    ): Response<ListRecipeResponse>

    /**
     * @author Sandra Martinez
     * peticiones get, delete y post de la lista de la compra con sus correspondientes headers
     */

    //GET
    @Headers(
        "Content-Type: application/json",
        "x-api-key: $API_KEY_SANDRA"
    )
    @GET("mealplanner/{username}/shopping-list")
    suspend fun getShoppingList(
        @Path("username") username: String,
        @Query("username") usernameQuery: String,
        @Query("hash") hash: String
    ): Response<ResponseGetShoppingList>

    //DELETE
    @Headers(
        "Content-Type: application/json",
        "x-api-key: $API_KEY_SANDRA"
    )
    @DELETE("mealplanner/{username}/shopping-list/items/{id}")
    suspend fun deleteItemShoppingList(
        @Path("username") username: String,
        @Path("id") itemId: Int,
        @Query("username") usernameQuery: String,
        @Query("id") itemIdQuery: Int,
        @Query("hash") hash: String
    ): Response<ResponseDeleteItem>

    //POST
    @Headers(
        "Content-Type: application/json",
        "x-api-key: $API_KEY_SANDRA"
    )
    @POST("mealplanner/{username}/shopping-list/items")
    suspend fun addItemShoppingList(
        @Body item: PostItem,
        @Path("username") username: String,
        @Query("username") usernameQuery: String,
        @Query("hash") hash: String
    ): Response<Item>
}