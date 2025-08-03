package com.example.codingtest_fauzanabdillah.features.home.presentation.presenters

import com.example.codingtest_fauzanabdillah.core.base.BasePresenter
import com.example.codingtest_fauzanabdillah.features.home.domain.repositories.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostPresenter(
    private val repository: PostRepository
) : BasePresenter<PostContract.View>(), PostContract.Presenter {

    override fun getPosts() {
        view?.showLoading()
        scope.launch {
            try {
                val posts = withContext(Dispatchers.IO) {
                    repository.getPosts()
                }
                view?.showPosts(posts)
            } catch (e: Exception) {
                view?.showError(e.message ?: "Unknown error")
            }
        }
    }

}