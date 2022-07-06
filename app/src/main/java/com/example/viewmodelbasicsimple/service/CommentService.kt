package com.example.viewmodelbasicsimple.service

import com.example.viewmodelbasicsimple.model.Comment
import com.example.viewmodelbasicsimple.model.ResponseListModelDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CommentService {

    // https://run.mocky.io/v3/8690f99b-3ddb-4051-bb4d-60133f651ae2
    @GET("review/list")
    fun getComments(
        @Query("productId") productId: Int,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Call<ResponseListModelDto<Comment>>
}