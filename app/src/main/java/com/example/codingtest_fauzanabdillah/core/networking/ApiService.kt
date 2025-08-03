package com.example.codingtest_fauzanabdillah.core.networking

import com.example.codingtest_fauzanabdillah.features.home.data.models.PostResponseModel
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<PostResponseModel>
}