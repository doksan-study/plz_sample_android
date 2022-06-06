package com.example.viewmodelbasicsimple.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelbasicsimple.model.Product
import com.example.viewmodelbasicsimple.service.ProductService
import com.example.viewmodelbasicsimple.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductViewModel(private val productId: Int) : ViewModel() {
    private val retrofit = RetrofitClient.retrofit
    private var _product: MutableLiveData<Product> = MutableLiveData<Product>()

    val product: LiveData<Product>
        get() = _product

    // todo 댓글에 대한 LiveData 필요

    init {
        loadProduct()
        loadComments()
    }

    private fun loadProduct() {
        retrofit.create(ProductService::class.java).getProduct()
            .enqueue(object : Callback<Product> {
                override fun onResponse(call: Call<Product>, response: Response<Product>) {
                    if (response.isSuccessful.not()) {
                        Log.d(TAG, "onResponse: $response")
                        return
                    }
                    _product.postValue(response.body()!!)
                }

                override fun onFailure(call: Call<Product>, t: Throwable) {
                    Log.e(TAG, "onFailure: $t")
                }

            })


    }

    private fun loadComments() {

    }

    class ViewModelFactory(private val productId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
                return ProductViewModel(productId) as T
            }
            throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }

    companion object {
        const val TAG = "ProductViewModel-TAG"
    }
}