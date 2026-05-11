package com.example.lab09.network

import com.example.lab09.model.PostModel
import retrofit2.http.GET
import retrofit2.http.Path

interface PostApiService {
    @GET("posts")
    suspend fun getUserPosts(): List<PostModel>

    @GET("posts/{id}")
    suspend fun getUserPostById(@Path("id") id: Int): PostModel
}