package com.example.trabajo_final_t3.data.retrofit

import com.example.trabajo_final_t3.data.models.ingredients.IngredientsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitInterface {

    companion object {
        const val API_KEY = "e6babe78c3164d15a137f94b95196593"
    }

    @Headers(
        "Content-Type: application/json",
        "x-api-key: $API_KEY"
    )
    @GET("food/ingredients/search")
    suspend fun getIngredients(
        @Query("query") ingredientName: String
    ): Response<IngredientsResponse>
}