package com.example.tabajo_finalt3.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObjeto {

    const val urlBase = ""

    private val builder = Retrofit.Builder()
        .baseUrl(urlBase)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitInterface: RetrofitInterface by lazy {
        builder.create(RetrofitInterface::class.java)
    }

}