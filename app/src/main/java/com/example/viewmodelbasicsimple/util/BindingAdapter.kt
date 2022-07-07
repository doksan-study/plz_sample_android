package com.example.viewmodelbasicsimple.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.viewmodelbasicsimple.viewmodel.ProductViewModel

object BindingAdapter {
    @BindingAdapter("price")
    @JvmStatic
    fun integerToPriceString(view: TextView, cost: Int){
        view.text = ProductViewModel.intToPriceString(cost)
    }

}