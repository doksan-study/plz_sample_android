package com.example.viewmodelbasicsimple.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelbasicsimple.model.Product
import com.example.viewmodelbasicsimple.service.RetrofitClient

class ProductViewModel(private val productId: Int) : ViewModel() {

    private var _product: Product
    val product: Product
        get() = _product

    // 댓글에 대한 LiveData 필요

    init {
        _product = loadProduct()
        loadComments()
    }

    private fun loadProduct(): Product {
        var id: Int = 0
        var name: String = "test Product"
        var description: String = "test description"
        var price: Int = 2000

        val retrofit = RetrofitClient.retrofit

        return Product(productId, name, description, price)
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
}