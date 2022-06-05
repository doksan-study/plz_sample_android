package com.example.viewmodelbasicsimple.service

import com.example.viewmodelbasicsimple.model.Product
import com.example.viewmodelbasicsimple.model.ProductDto
import retrofit2.Call
import retrofit2.http.GET

interface ProductService {
    // https://run.mocky.io/v3/9fb33b80-8b3a-4c9f-b608-764bc60eae98
    @GET("v3/9fb33b80-8b3a-4c9f-b608-764bc60eae98")
    fun getProducts(): Call<ProductDto>

    // https://run.mocky.io/v3/8690f99b-3ddb-4051-bb4d-60133f651ae2
    @GET("v3/8690f99b-3ddb-4051-bb4d-60133f651ae2")
    fun getProduct(): Call<Product>

}