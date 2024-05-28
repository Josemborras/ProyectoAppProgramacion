package com.example.trabajo_final_t3.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object RetrofitObjeto {

    const val urlBase = "https://api.spoonacular.com/"

    //para evitar que la aplicación se cierre si hay un error timeout
    val okHttpClient = OkHttpClient.Builder()
        .readTimeout(60000, TimeUnit.SECONDS)
        .connectTimeout(60000, TimeUnit.SECONDS)
        .writeTimeout(60000, TimeUnit.SECONDS)
        .build()

    private val builder = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(urlBase)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitInterface: RetrofitInterface by lazy {
        builder.create(RetrofitInterface::class.java)
    }

}