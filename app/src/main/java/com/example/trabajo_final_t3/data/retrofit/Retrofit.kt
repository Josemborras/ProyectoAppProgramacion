package com.example.trabajo_final_t3.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Retrofit {
    private const val URL_BASE = "https://api.spoonacular.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService by lazy { retrofit.create(RetrofitInterface::class.java) }
}