package com.example.viewmodelbasicsimple.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Comment(
    @SerializedName("id") val id: Int,
    @SerializedName("productId") val productId: Int,
    @SerializedName("contents") val contents: String,
    @SerializedName("createdAt") val createdAt: Date,
)

data class CommentDto(
    @SerializedName("items") val items: ArrayList<Comment>
)