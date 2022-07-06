package com.example.viewmodelbasicsimple.service

import com.example.viewmodelbasicsimple.model.Product
import com.example.viewmodelbasicsimple.model.ResponseListModelDto
import com.example.viewmodelbasicsimple.model.ResponseModelDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {
    @GET("product/list")
    fun getProducts(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Call<ResponseListModelDto<Product>>

    @GET("product/{productId}")
    fun getProduct(
        @Path("productId") productId: Int
    ): Call<ResponseModelDto<Product>>

    @GET("search/product")
    fun searchProducts(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("word") word: String
    ):Call<ResponseListModelDto<Product>>

}

