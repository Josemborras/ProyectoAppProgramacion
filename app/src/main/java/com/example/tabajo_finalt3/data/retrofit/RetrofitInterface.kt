package com.example.tabajo_finalt3.data.retrofit

import com.example.tabajo_finalt3.data.models.ResponseDeleteItem
import com.example.tabajo_finalt3.data.models.ResponseGetShoppingList
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitInterface {
    /**
     * @author Sandra Martinez
     * peticiones get y delete de la lista de la compra
     */
    @Headers(
        "Content-Type: application/json",
        "x-api-key: 90b3b4e1f1e64864943fe169af738877"
    )
    @GET("mealplanner/{username}/shopping-list")
    suspend fun getShoppingList(
        @Path("username") username: String,
        @Query("username") usernameQuery: String,
        @Query("hash") hash: String
    ): Response<ResponseGetShoppingList>

    @Headers(
        "Content-Type: application/json",
        "x-api-key: 90b3b4e1f1e64864943fe169af738877"
    )
    @DELETE("mealplanner/{username}/shopping-list/items/{id}")
    suspend fun deleteItemShoppingList(
        @Path("username") username: String,
        @Path("id") itemId: Int,
        @Query("username") usernameQuery: String,
        @Query("id") itemIdQuery: Int,
        @Query("hash") hash: String
    ): Response<ResponseDeleteItem>
}