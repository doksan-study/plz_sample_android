package com.example.viewmodelbasicsimple.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.viewmodelbasicsimple.databinding.ProductItemBinding
import com.example.viewmodelbasicsimple.model.Product

class ProductAdapter(private val clickCallback: () -> Unit) : ListAdapter<Product, ProductAdapter.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ProductItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            product = currentList[position]
            root.setOnClickListener { clickCallback.invoke() }
        }
    }


    inner class ViewHolder(val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem == newItem
            }
        }
    }
}