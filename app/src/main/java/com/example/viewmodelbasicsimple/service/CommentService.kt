package com.example.viewmodelbasicsimple.service

import com.example.viewmodelbasicsimple.model.CommentDto
import retrofit2.Call
import retrofit2.http.GET

interface CommentService {

    // https://run.mocky.io/v3/8690f99b-3ddb-4051-bb4d-60133f651ae2
    @GET("v3/8690f99b-3ddb-4051-bb4d-60133f651ae2")
    fun getComments(productId: String): Call<CommentDto>
}