package com.example.codingtest_fauzanabdillah.posts

import com.example.codingtest_fauzanabdillah.features.home.data.datasources.PostRemoteDatasource
import com.example.codingtest_fauzanabdillah.features.home.data.models.PostResponseModel
import com.example.codingtest_fauzanabdillah.features.home.data.repositories.PostRepositoryImpl
import com.example.codingtest_fauzanabdillah.features.home.domain.models.PostModel
import com.example.codingtest_fauzanabdillah.features.home.domain.repositories.PostRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class PostRepositoryImplTest {

    private lateinit var repository: PostRepository
    private val mockRemoteDatasource: PostRemoteDatasource = mock()

    @Before
    fun setup() {
        repository = PostRepositoryImpl(mockRemoteDatasource)
    }

    // menguji keberhasil mapping response model ke model yang di butuhkan UI
    @Test
    fun `getPosts should return mapped PostModel from PostResponseModel`() = runTest {
        val mockResponse = listOf(
            PostResponseModel(id = 1, title = "Title 1", body = "Body 1", userId = 1),
            PostResponseModel(id = 2, title = "Title 2", body = "Body 2", userId = 1)
        )

        val expectedMapped = listOf(
            PostModel(
                id = 1,
                title = "Title 1",
                body = "Body 1",
                imageURL = "https://picsum.photos/id/1/200/200"
            ),
            PostModel(
                id = 2,
                title = "Title 2",
                body = "Body 2",
                imageURL = "https://picsum.photos/id/2/200/200"
            )
        )

        whenever(mockRemoteDatasource.getPosts()).thenReturn(mockResponse)

        val result = repository.getPosts()

        assertEquals(expectedMapped, result)
        verify(mockRemoteDatasource).getPosts()
    }
}
