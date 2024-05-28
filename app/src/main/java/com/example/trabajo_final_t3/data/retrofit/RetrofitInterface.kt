package com.example.trabajo_final_t3.data.retrofit

import com.example.trabajo_final_t3.data.models.RecipeCard
import com.example.trabajo_final_t3.data.models.RecipesRandom
import com.example.trabajo_final_t3.data.models.TriviaRandom
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitInterface {

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


}