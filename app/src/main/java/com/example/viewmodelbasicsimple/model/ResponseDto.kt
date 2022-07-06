package com.example.viewmodelbasicsimple.model

import com.google.gson.annotations.SerializedName


data class ResponseModelDto<T>(
    @SerializedName("message") val message: String,
    @SerializedName("data") val item : T
)

data class ResponseListModelDto<T>(
    @SerializedName("message") val message: String,
    @SerializedName("data") val items: ArrayList<T>
)