package com.example.viewmodelbasicsimple.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelbasicsimple.model.Product
import com.example.viewmodelbasicsimple.model.ResponseModelDto
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

    init {
        loadProduct()
    }

    private fun loadProduct(): Product? {
        var resultProduct: Product? = null
        retrofit.create(ProductService::class.java).getProduct(productId)
            .enqueue(object : Callback<ResponseModelDto<Product>> {
                override fun onResponse(
                    call: Call<ResponseModelDto<Product>>,
                    response: Response<ResponseModelDto<Product>>
                ) {
                    if (response.isSuccessful.not()) {
                        Log.d(TAG, "onResponse: $response")
                        return
                    }
                    response.body()?.let { dto ->
                        Log.d(TAG, "onResponse: >> \t${response.body()!!}")
                        _product.postValue(dto.item)
                        resultProduct = dto.item
                    }
                }

                override fun onFailure(call: Call<ResponseModelDto<Product>>, t: Throwable) {
                    Log.e(TAG, "onFailure: $t")
                }

            })

        return resultProduct

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

        fun intToPriceString(cost: Int): String {
            var tempList = ArrayList<Char>()
            var tempCost = cost
            var tempIdx = 0
            while (tempCost > 0) {
                tempIdx++
                val num = tempCost % 10
                tempCost /= 10
                tempList.add((num.digitToChar()))
                if (tempIdx % 3 == 0) tempList.add(',')
            }

            var result = "원"
            for (ch in tempList)
                result = ch + result

            return result
        }
    }
}