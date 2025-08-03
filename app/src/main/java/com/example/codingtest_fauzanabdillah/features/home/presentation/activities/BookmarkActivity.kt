package com.example.codingtest_fauzanabdillah.features.home.presentation.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.codingtest_fauzanabdillah.databinding.ActivityBookmarkBinding
import com.example.codingtest_fauzanabdillah.features.home.data.datasources.BookmarkPreferenceService
import com.example.codingtest_fauzanabdillah.features.home.presentation.adapters.PostAdapter

class BookmarkActivity : AppCompatActivity() {
    companion object {
        fun getStartIntent(context: Context): Intent {
            val intent = Intent(context, BookmarkActivity::class.java)
            return intent
        }
    }

    private lateinit var binding: ActivityBookmarkBinding
    private val postAdapter by lazy { PostAdapter() }
    private val bookmarkPreferenceService by lazy { BookmarkPreferenceService(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBookmarkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getBookmarks()

        setupRecyclerView()
        setupButton()
    }

    private fun getBookmarks() {
        val bookmarks = bookmarkPreferenceService.getBookmarks()
        if (bookmarks.isEmpty()) {
            binding.rvPosts.visibility = View.GONE
            binding.tvEmptyBookmark.visibility = View.VISIBLE
            postAdapter.clear()
        } else {
            binding.rvPosts.visibility = View.VISIBLE
            binding.tvEmptyBookmark.visibility = View.GONE
            postAdapter.apply {
                clear()
                setData(bookmarks)
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvPosts.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = postAdapter
        }
    }

    private fun setupButton() {
        binding.toolbar.setNavigationOnClickListener { finish() }
        postAdapter.onBookmarkClicked = { data ->
            if (bookmarkPreferenceService.isBookmarked(data.id)) {
                bookmarkPreferenceService.removeBookmark(data.id)
                postAdapter.removeData(data.id)

                // remove when bookmark is empty
                val bookmarks = bookmarkPreferenceService.getBookmarks()
                if (bookmarks.isEmpty()) {
                    binding.rvPosts.visibility = View.GONE
                    binding.tvEmptyBookmark.visibility = View.VISIBLE
                    postAdapter.clear()
                }
            } else {
                bookmarkPreferenceService.addBookmark(data)
            }
        }
    }
}