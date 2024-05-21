package com.example.tabajo_finalt3.data

import com.example.tabajo_finalt3.data.retrofit.RetrofitObjeto

class Repository {

    suspend fun recipesRandomAdd(number: Int) = RetrofitObjeto.retrofitInterface.rescipesRandom(number)

    suspend fun triviaRandomAdd() = RetrofitObjeto.retrofitInterface.triviaRandom()

}