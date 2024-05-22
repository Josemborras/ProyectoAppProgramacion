package com.example.trabajo_finalt3.model


import com.example.trabajo_finalt3.model.data.CardImage.CardImage
import com.example.trabajo_finalt3.model.data.Recipe.RecipeResponse
import com.example.trabajo_finalt3.model.retrofit.RetrofitHelper
import retrofit2.Response

class Repositorio {

    private val retrofit = RetrofitHelper.myService


    suspend fun getRecipe(id: Int): Response<RecipeResponse> {
        return retrofit.obtenerRecipe(id)
    }
    suspend fun getCardImage(id: Int): Response<CardImage> {
        return retrofit.obtenerCardImage(id)
    }
    suspend fun listRecipes(id: Int) = retrofit.obtenerRecetasSimilares(id)

    suspend fun getInstructions(id: Int) = retrofit.obtenerInstructions(id)
}