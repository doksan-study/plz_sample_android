package com.example.viewmodelbasicsimple.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelbasicsimple.model.Comment
import com.example.viewmodelbasicsimple.model.ResponseListModelDto
import com.example.viewmodelbasicsimple.service.CommentService
import com.example.viewmodelbasicsimple.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentListViewModel(private val productId: Int) : ViewModel() {
    private val retrofit = RetrofitClient.retrofit
    private val _comments: MutableLiveData<List<Comment>> by lazy {
        MutableLiveData<List<Comment>>().also {
            loadComments()
        }
    }

    val comments: LiveData<List<Comment>>
        get() = _comments

    private fun loadComments(): ArrayList<Comment>? {
        var resultComments: ArrayList<Comment>? = null
        retrofit.create(CommentService::class.java).getComments(productId, 1, 10)
            .enqueue(object : Callback<ResponseListModelDto<Comment>> {
                override fun onResponse(call: Call<ResponseListModelDto<Comment>>, response: Response<ResponseListModelDto<Comment>>) {
                    if (response.isSuccessful.not()) {
                        Log.e(TAG, "onResponse: Response Fail")
                        return
                    }
                    response.body()?.let {
                        _comments.postValue(it.items)
                        Log.d(TAG, "ResponseModel>> ${it.items} ")
                        resultComments = it.items
                    }
                }

                override fun onFailure(call: Call<ResponseListModelDto<Comment>>, t: Throwable) {
                    Log.e(TAG, "onFailure: $t")
                }

            })
        return resultComments
    }

    class ViewModelFactory(private val productId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CommentListViewModel::class.java)) {
                return CommentListViewModel(productId) as T
            }
            throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }

    companion object {
        const val TAG = "COMMENT_LIST_VIEW_MODEL-TAG"
    }
}