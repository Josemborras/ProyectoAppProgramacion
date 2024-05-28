package com.example.trabajo_final_t3.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObjeto {

    const val urlBase = "https://api.spoonacular.com/"

    private val builder = Retrofit.Builder()
        .baseUrl(urlBase)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitInterface: RetrofitInterface by lazy {
        builder.create(RetrofitInterface::class.java)
    }

}