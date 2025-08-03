package com.example.codingtest_fauzanabdillah.features.home.presentation.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.codingtest_fauzanabdillah.R
import com.example.codingtest_fauzanabdillah.core.util.LoadingDialogUtil
import com.example.codingtest_fauzanabdillah.databinding.ActivityDashboardBinding
import com.example.codingtest_fauzanabdillah.features.home.domain.models.PostModel
import com.example.codingtest_fauzanabdillah.features.home.presentation.adapters.PostAdapter
import com.example.codingtest_fauzanabdillah.features.home.presentation.presenters.PostContract
import com.example.codingtest_fauzanabdillah.features.home.presentation.presenters.PostPresenter
import org.koin.android.ext.android.inject

class DashboardActivity : AppCompatActivity(), PostContract.View {
    companion object {
        const val USERNAME_ARG = "USERNAME_ARG"

        fun getStartIntent(context: Context, username: String): Intent {
            val intent = Intent(context, DashboardActivity::class.java)
            intent.putExtra(USERNAME_ARG, username)
            return intent
        }
    }

    private lateinit var binding: ActivityDashboardBinding
    private val presenter: PostPresenter by inject()
    private val postAdapter by lazy { PostAdapter() }

    private var username = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter.attachView(this)
        presenter.getPosts()

        getArguments()

        setupRecyclerView()
        setupAdapter()
    }

    private fun getArguments() {
        username = intent.getStringExtra(USERNAME_ARG) ?: ""
        binding.toolbar.title = getString(R.string.welcome_name, username)
    }

    private fun setupRecyclerView() {
        binding.rvPosts.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = postAdapter
        }
    }

    private fun setupAdapter() {
        postAdapter.onBookmarkClicked = { data ->
            // TODO : save to local storage
        }
    }

    override fun showPosts(posts: List<PostModel>) {
        LoadingDialogUtil.hide()
        postAdapter.apply {
            clear()
            setData(posts)
        }
    }

    override fun showLoading() {
        LoadingDialogUtil.show(this)
    }

    override fun showError(message: String) {
        LoadingDialogUtil.hide()
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}