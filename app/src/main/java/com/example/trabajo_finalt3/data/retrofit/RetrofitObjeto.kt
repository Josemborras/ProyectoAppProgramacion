package com.example.trabajo_finalt3.data.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitObjeto {

    const val URL_BASE = "https://api.spoonacular.com/"

    //para evitar que la aplicación se cierre si hay un error timeout
    val okHttpClient = OkHttpClient.Builder()
        .readTimeout(60000, TimeUnit.SECONDS)
        .connectTimeout(60000, TimeUnit.SECONDS)
        .writeTimeout(60000, TimeUnit.SECONDS)
        .build()

    private val builder = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitInterface: RetrofitInterface by lazy {
        builder.create(RetrofitInterface::class.java)
    }

}