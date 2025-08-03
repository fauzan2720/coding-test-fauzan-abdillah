package com.example.codingtest_fauzanabdillah.features.home.domain.repositories

import com.example.codingtest_fauzanabdillah.features.home.domain.models.PostModel

interface PostRepository {
    suspend fun getPosts(): List<PostModel>
}