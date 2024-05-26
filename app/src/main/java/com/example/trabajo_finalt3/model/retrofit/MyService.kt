package com.example.trabajo_finalt3.model.retrofit

import com.example.trabajo_finalt3.model.data.CardImage.CardImage
import com.example.trabajo_finalt3.model.data.ListRecipe.RecipeItem
import com.example.trabajo_finalt3.model.data.Recipe.RecipeResponse
import com.example.trabajo_finalt3.model.data.Steps.StepsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface MyService {

    @Headers("x-api-key: 6ae3b4a55ffc4189ba718e387513a9f2","Content-Type: application/json")
    @GET("recipes/{id}/information")
    suspend fun obtenerRecipe(@Path("id") id: Int): Response<RecipeResponse>

    @Headers("x-api-key: 6ae3b4a55ffc4189ba718e387513a9f2","Content-Type: application/json")
    @GET("recipes/{id}/similar")
    suspend fun obtenerRecetasSimilares(@Path("id") id: Int): Response<ArrayList<RecipeItem>>

    @Headers("x-api-key: 6ae3b4a55ffc4189ba718e387513a9f2","Content-Type: application/json")
    @GET("recipes/{id}/card")
    suspend fun obtenerCardImage(@Path("id") id: Int): Response<CardImage>

    @Headers("x-api-key: 6ae3b4a55ffc4189ba718e387513a9f2","Content-Type: application/json")
    @GET("recipes/{id}/analyzedInstructions")
    suspend fun obtenerInstructions(@Path("id") id: Int): Response<StepsResponse>

}