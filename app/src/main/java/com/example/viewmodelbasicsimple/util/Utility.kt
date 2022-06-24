package com.example.viewmodelbasicsimple.util

import android.content.Context
import android.widget.Toast

object Utility {

    fun isValidSearchWord(word: String?, context: Context?): Boolean{
        /** Search Word must not be Null Or Empty */
        if (word.isNullOrEmpty()){
            context?.let { Toast.makeText(context, "검색어를 입력해 주세요.", Toast.LENGTH_SHORT).show() }
            return false
        }

        /** Search Word's length must be at least 2 digit */
        if(word!!.length < 2){
            context?.let { Toast.makeText(context, "검색어를 최소 2글자 이상 입력해주세요.", Toast.LENGTH_SHORT).show() }
            println("검색어를 최소 2글자 이상 입력해주세요.")
            return false
        }

        return true
    }
}
