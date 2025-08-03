package com.example.codingtest_fauzanabdillah.features.home.data.models

import android.os.Parcelable
import com.example.codingtest_fauzanabdillah.features.home.domain.models.PostModel
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostResponseModel(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("body")
    val body: String? = null,

    @field:SerializedName("userId")
    val userId: Int? = null
) : Parcelable

fun PostResponseModel.toDomain(): PostModel {
    return PostModel(
        id = id ?: 0,
        title = this.title.orEmpty(),
        body = this.body.orEmpty(),
        imageURL = "https://picsum.photos/id/${this.id}/200/200"
    )
}

fun List<PostResponseModel>.toDomainList(): List<PostModel> {
    return this.map { it.toDomain() }
}
