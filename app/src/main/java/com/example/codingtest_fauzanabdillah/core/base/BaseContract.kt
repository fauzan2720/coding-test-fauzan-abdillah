package com.example.codingtest_fauzanabdillah.core.base

interface BaseContract {
    interface View {
        fun showLoading()
        fun showError(message: String)
    }

    interface Presenter {
        fun onDestroy()
    }
}
