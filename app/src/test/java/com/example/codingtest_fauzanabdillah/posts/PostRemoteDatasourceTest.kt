package com.example.codingtest_fauzanabdillah.posts

import com.example.codingtest_fauzanabdillah.core.networking.ApiService
import com.example.codingtest_fauzanabdillah.features.home.data.datasources.PostRemoteDatasource
import com.example.codingtest_fauzanabdillah.features.home.data.models.PostResponseModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class PostRemoteDatasourceTest {
    private lateinit var apiService: ApiService
    private lateinit var datasource: PostRemoteDatasource

    @Before
    fun setup() {
        apiService = mock(ApiService::class.java)
        datasource = PostRemoteDatasource(apiService)
    }

    // kebutuhan data = id, title, body
    // menguji ketersediaan data pada api
    @Test
    fun `getPosts should return list of posts with expected id, title, and body`() = runTest {
        val mockPosts = listOf(
            PostResponseModel(id = 1, title = "Post 1", body = "Body 1", userId = 1),
            PostResponseModel(id = 2, title = "Post 2", body = "Body 2", userId = 1),
        )

        `when`(apiService.getPosts()).thenReturn(mockPosts)

        val result = datasource.getPosts()

        val expectedSubset = mockPosts.map { Triple(it.id, it.title, it.body) }
        val actualSubset = result.map { Triple(it.id, it.title, it.body) }

        assertEquals(expectedSubset, actualSubset)
        verify(apiService).getPosts()
    }
}