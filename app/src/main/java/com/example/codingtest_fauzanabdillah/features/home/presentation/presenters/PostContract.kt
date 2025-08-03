package com.example.codingtest_fauzanabdillah.features.home.presentation.presenters

import com.example.codingtest_fauzanabdillah.core.base.BaseContract
import com.example.codingtest_fauzanabdillah.features.home.domain.models.PostModel

interface PostContract {
    interface View : BaseContract.View {
        fun showPosts(posts: List<PostModel>)
    }

    interface Presenter : BaseContract.Presenter {
        fun getPosts()
    }
}