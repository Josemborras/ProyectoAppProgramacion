package com.example.tabajo_finalt3.data.retrofit

import com.example.tabajo_finalt3.data.models.RecipesRandom
import com.example.tabajo_finalt3.data.models.TriviaRandom
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
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


}