package com.example.codingtest_fauzanabdillah.core.di

import com.example.codingtest_fauzanabdillah.core.networking.ApiClient
import com.example.codingtest_fauzanabdillah.features.home.data.datasources.PostRemoteDatasource
import com.example.codingtest_fauzanabdillah.features.home.data.repositories.PostRepositoryImpl
import com.example.codingtest_fauzanabdillah.features.home.domain.repositories.PostRepository
import com.example.codingtest_fauzanabdillah.features.home.presentation.presenters.PostPresenter
import org.koin.dsl.module

val appModule = module {

    // Retrofit API
    single { ApiClient.apiService }

    // Post
    single { PostRemoteDatasource(get()) }
    single<PostRepository> { PostRepositoryImpl(get()) }
    factory { PostPresenter(get()) }

}
