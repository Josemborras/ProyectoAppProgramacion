package com.example.trabajo_finalt3.model.retrofit

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private val BASE_URL = "https://api.spoonacular.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okhttpClient())
        .build()

    val myService by lazy {
        retrofit.create(MyService::class.java)
    }

    private fun okhttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor {
            Log.d("MI ETIQUETA", it)
        }
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        return client
    }
}