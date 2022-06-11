package com.example.viewmodelbasicsimple.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.viewmodelbasicsimple.model.Comment
import com.example.viewmodelbasicsimple.model.CommentDto
import com.example.viewmodelbasicsimple.service.CommentService
import com.example.viewmodelbasicsimple.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentListViewModel(private val productId: String) : ViewModel() {
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
        retrofit.create(CommentService::class.java).getComments(productId)
            .enqueue(object : Callback<CommentDto> {
                override fun onResponse(call: Call<CommentDto>, response: Response<CommentDto>) {
                    if (response.isSuccessful.not()) {
                        Log.e(TAG, "onResponse: Response Fail")
                        return
                    }
                    response.body()?.let {
                        _comments.postValue(it.items)
                        resultComments = it.items
                    }
                }

                override fun onFailure(call: Call<CommentDto>, t: Throwable) {
                    Log.e(TAG, "onFailure: $t", )
                }

            })
        return resultComments
    }

    companion object {
        const val TAG = "COMMENT_LIST_VIEW_MODEL-TAG"
    }
}