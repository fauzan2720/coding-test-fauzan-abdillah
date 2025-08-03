package com.example.codingtest_fauzanabdillah.features.home.data.datasources

import com.example.codingtest_fauzanabdillah.core.networking.ApiService
import com.example.codingtest_fauzanabdillah.features.home.data.models.PostResponseModel

class PostRemoteDatasource(private val apiService: ApiService) {
    suspend fun getPosts(): List<PostResponseModel> {
        return apiService.getPosts()
    }
}