package com.example.lab09.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // Cliente para JSONPlaceholder (el que ya usabas)
    private const val BASE_URL_JSONPLACEHOLDER = "https://jsonplaceholder.typicode.com/"

    val jsonPlaceholderApiService: PostApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_JSONPLACEHOLDER)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostApiService::class.java)
    }

    // Cliente para DummyJSON (nuevo)
    private const val BASE_URL_DUMMYJSON = "https://dummyjson.com/"

    val dummyJsonApiService: ProductApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_DUMMYJSON)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductApiService::class.java)
    }
}