package com.example.codingtest_fauzanabdillah.features.home.presentation.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.codingtest_fauzanabdillah.R
import com.example.codingtest_fauzanabdillah.core.base.BaseActivity
import com.example.codingtest_fauzanabdillah.core.util.LoadingDialogUtil
import com.example.codingtest_fauzanabdillah.databinding.ActivityDashboardBinding
import com.example.codingtest_fauzanabdillah.features.home.data.datasources.BookmarkPreferenceService
import com.example.codingtest_fauzanabdillah.features.home.domain.models.PostModel
import com.example.codingtest_fauzanabdillah.features.home.presentation.adapters.PostAdapter
import com.example.codingtest_fauzanabdillah.features.home.presentation.presenters.PostContract
import com.example.codingtest_fauzanabdillah.features.home.presentation.presenters.PostPresenter
import org.koin.android.ext.android.inject

class DashboardActivity : BaseActivity(), PostContract.View {
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
    private val bookmarkPreferenceService by lazy { BookmarkPreferenceService(this) }

    private var username = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        applyWindowInsetsTo(binding.root)

        presenter.attachView(this)
        presenter.getPosts()

        getArguments()

        setupRecyclerView()
        setupButton()
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

    private fun setupButton() {
        postAdapter.onBookmarkClicked = { data ->
            if (bookmarkPreferenceService.isBookmarked(data.id)) {
                bookmarkPreferenceService.removeBookmark(data.id)
            } else {
                bookmarkPreferenceService.addBookmark(data)
            }
        }
        binding.ivBookmark.setOnClickListener {
            val intent = BookmarkActivity.getStartIntent(this)
            startActivity(intent)
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