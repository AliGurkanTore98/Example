package com.codingurkan.example.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    var retrofit: Retrofit? = null


    fun getClient(): Retrofit {
        if (retrofit == null)
            retrofit = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit as Retrofit
    }
}