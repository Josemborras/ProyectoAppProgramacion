package com.example.trabajo_finalt3.data

import com.example.trabajo_finalt3.data.models.Item
import com.example.trabajo_finalt3.data.models.PostItem
import com.example.trabajo_finalt3.data.models.ResponseDeleteItem
import com.example.trabajo_finalt3.data.retrofit.RetrofitObjeto
import retrofit2.Response

class Repository {
    private val retrofit by lazy {
        RetrofitObjeto.retrofitInterface
    }

    /**
     * @author Sandra Martinez
     * peticiones get, delete y post de la lista de la compra
     * Los parametros del usuario tendran un valor por defecto
     */

    //GET
    suspend fun getShoppingList(
        username: String = "sandra-m",
        usernameQuery: String = "sandra-m",
        hash: String = "56d9160fc457a3d586fe75d3f885da91654a26cd"
    ) = retrofit.getShoppingList(username, usernameQuery, hash)

    //DELETE
    suspend fun deleteItemShoppingList(itemId: Int): Response<ResponseDeleteItem> {
        val username = "sandra-m"
        val usernameQuery = "sandra-m"
        val hash= "56d9160fc457a3d586fe75d3f885da91654a26cd"

        return retrofit.deleteItemShoppingList(username, itemId, usernameQuery, itemId, hash)
    }

    //POST
    suspend fun addItemShoppingList(item: PostItem): Response<Item> {
        val username = "sandra-m"
        val usernameQuery = "sandra-m"
        val hash= "56d9160fc457a3d586fe75d3f885da91654a26cd"

        return retrofit.addItemShoppingList(item, username, usernameQuery, hash)
    }

}