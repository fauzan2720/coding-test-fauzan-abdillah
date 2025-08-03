package com.example.codingtest_fauzanabdillah.features.home.data.repositories

import com.example.codingtest_fauzanabdillah.features.home.data.datasources.PostRemoteDatasource
import com.example.codingtest_fauzanabdillah.features.home.data.models.toDomainList
import com.example.codingtest_fauzanabdillah.features.home.domain.models.PostModel
import com.example.codingtest_fauzanabdillah.features.home.domain.repositories.PostRepository

class PostRepositoryImpl(private val datasource: PostRemoteDatasource) : PostRepository {
    override suspend fun getPosts(): List<PostModel> {
        val result = datasource.getPosts()
        return result.toDomainList()
    }
}
