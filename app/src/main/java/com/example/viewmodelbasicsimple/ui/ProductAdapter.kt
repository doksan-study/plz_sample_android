package com.example.viewmodelbasicsimple.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.viewmodelbasicsimple.databinding.ProductItemBinding
import com.example.viewmodelbasicsimple.model.Product

class ProductAdapter(private val clickCallback: (Product) -> Unit) :
    ListAdapter<Product, ProductAdapter.ViewHolder>(diffUtil) {

    /** Create new views (invoked by the layout manager) **/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        return ViewHolder(
            ProductItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    /** Replace the contents of a view (invoked by the layout manager) **/
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        holder.binding.apply {
            product = currentList[position]
        }
    }


    inner class ViewHolder(val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

            init {
                /** Define click listener for the ViewHolder's View. **/
                binding.root.setOnClickListener { clickCallback.invoke(currentList[position]) }
            }
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