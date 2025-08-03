package com.example.codingtest_fauzanabdillah.features.home.data.datasources

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.codingtest_fauzanabdillah.features.home.domain.models.PostModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BookmarkPreferenceService(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("bookmark_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    companion object {
        private const val KEY_BOOKMARKS = "KEY_BOOKMARKS"
    }

    private fun saveBookmarks(bookmarks: List<PostModel>) {
        val json = gson.toJson(bookmarks)
        prefs.edit { putString(KEY_BOOKMARKS, json) }
    }

    fun getBookmarks(): List<PostModel> {
        val json = prefs.getString(KEY_BOOKMARKS, null) ?: return emptyList()
        val type = object : TypeToken<List<PostModel>>() {}.type
        return gson.fromJson(json, type)
    }

    fun addBookmark(post: PostModel) {
        val current = getBookmarks().toMutableList()
        if (current.none { it.id == post.id }) {
            current.add(post)
            saveBookmarks(current)
        }
    }

    fun removeBookmark(postId: Int) {
        val current = getBookmarks().toMutableList()
        current.removeAll { it.id == postId }
        saveBookmarks(current)
    }

    fun isBookmarked(postId: Int): Boolean {
        return getBookmarks().any { it.id == postId }
    }
}
