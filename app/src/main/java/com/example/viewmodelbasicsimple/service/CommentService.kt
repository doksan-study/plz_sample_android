package com.example.viewmodelbasicsimple.service

import com.example.viewmodelbasicsimple.model.Comment
import com.example.viewmodelbasicsimple.model.ResponseListModelDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CommentService {

    @GET("review/list")
    fun getComments(
        @Query("productId") productId: Int,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Call<ResponseListModelDto<Comment>>
}