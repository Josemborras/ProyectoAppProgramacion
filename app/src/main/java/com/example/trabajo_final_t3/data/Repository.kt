package com.example.trabajo_final_t3.data

import com.example.trabajo_final_t3.data.retrofit.RetrofitObjeto

class Repository {

    suspend fun recipesRandomAdd(number: Int) = RetrofitObjeto.retrofitInterface.rescipesRandom(number)

    suspend fun triviaRandomAdd() = RetrofitObjeto.retrofitInterface.triviaRandom()

    suspend fun recipeCard(id: Int) = RetrofitObjeto.retrofitInterface.recipeCard(id)

}