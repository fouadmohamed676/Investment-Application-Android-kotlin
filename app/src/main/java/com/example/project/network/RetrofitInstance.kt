package com.example.project.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val baseUrl = URL.BaseUrlApi

object RetrofitInstance {

    private val retrofit by lazy {

        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }


    val api: NetworkApi by lazy {
        retrofit.create(NetworkApi::class.java)

    }


}