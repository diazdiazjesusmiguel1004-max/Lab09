package com.example.lab09.network

import com.example.lab09.model.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApiService {
    @GET("products")
    suspend fun getProducts(): ProductResponse

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): com.example.lab09.model.ProductModel
}