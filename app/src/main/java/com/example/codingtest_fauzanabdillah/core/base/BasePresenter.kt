package com.example.codingtest_fauzanabdillah.core.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

abstract class BasePresenter<V : BaseContract.View> : BaseContract.Presenter {

    protected var view: V? = null
    private val job = SupervisorJob()
    protected val scope = CoroutineScope(Dispatchers.Main + job)

    fun attachView(view: V) {
        this.view = view
    }

    override fun onDestroy() {
        job.cancel()
        view = null
    }
}