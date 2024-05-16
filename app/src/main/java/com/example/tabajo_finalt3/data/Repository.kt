package com.example.tabajo_finalt3.data

import com.example.tabajo_finalt3.data.retrofit.RetrofitObjeto

class Repository {

    suspend fun recipesRandomAdd() = RetrofitObjeto.retrofitInterface.rescipesRandom(number = 10)

    suspend fun triviaRandomAdd() = RetrofitObjeto.retrofitInterface.triviaRandom()

}