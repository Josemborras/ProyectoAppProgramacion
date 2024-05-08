package com.example.tabajo_finalt3.data

import com.example.tabajo_finalt3.data.models.ResponseDeleteItem
import com.example.tabajo_finalt3.data.retrofit.RetrofitObjeto
import retrofit2.Response

class Repository {
    private val retrofit by lazy {
        RetrofitObjeto.retrofitInterface
    }

    /**
     * @author Sandra Martinez
     * peticiones get y delete de la lista de la compra
     * Los parametros del usuario tendran un valor por defecto
     */
    suspend fun getShoppingList(
        username: String = "sandra-m",
        usernameQuery: String = "sandra-m",
        hash: String = "56d9160fc457a3d586fe75d3f885da91654a26cd"
    ) = retrofit.getShoppingList(username, usernameQuery, hash)
    suspend fun deleteItemShoppingList(itemId: Int): Response<ResponseDeleteItem> {
        val username = "sandra-m"
        val usernameQuery = "sandra-m"
        val hash= "56d9160fc457a3d586fe75d3f885da91654a26cd"

        return retrofit.deleteItemShoppingList(username, itemId, usernameQuery, itemId, hash)
    }

}