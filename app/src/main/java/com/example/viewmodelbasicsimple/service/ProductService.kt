package com.example.viewmodelbasicsimple.service

import com.example.viewmodelbasicsimple.model.Product
import com.example.viewmodelbasicsimple.model.ResponseListModelDto
import com.example.viewmodelbasicsimple.model.ResponseModelDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {
    // https://run.mocky.io/v3/caa53bd1-e927-46c6-a31f-8e33667770de
    @GET("v3/caa53bd1-e927-46c6-a31f-8e33667770de")
//    @GET("product/list")
    fun getProducts(
//        @Query("page") page: Int,
//        @Query("limit") limit: Int
    ): Call<ResponseListModelDto<Product>>

    @GET("product/{productId}")
    fun getProduct(
        @Path("productId") productId: Int
    ): Call<ResponseModelDto<Product>>

//    @GET("search/product")
    // https://run.mocky.io/v3/0bcd88e8-2feb-4d1e-aa5e-78c0d2dd2387
    @GET("v3/0bcd88e8-2feb-4d1e-aa5e-78c0d2dd2387")
    fun searchProducts(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("word") word: String
    ):Call<ResponseListModelDto<Product>>

}

