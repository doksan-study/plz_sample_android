package com.example.viewmodelbasicsimple.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.viewmodelbasicsimple.databinding.CommentItemBinding
import com.example.viewmodelbasicsimple.model.Comment


class CommentAdapter : ListAdapter<Comment, CommentAdapter.ViewHolder>(diffUtil) {

    /** Create new views (invoked by the layout manager) **/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        return ViewHolder(
            CommentItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    /** Replace the contents of a view (invoked by the layout manager).
     *  Get element from your dataset at this position and replace the
     *  contents of the view with that element
     *
     * **/
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            comment = currentList[position]
        }
    }

    inner class ViewHolder(val binding: CommentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            /** Define click listener for the ViewHolder's View. **/
            binding.root
        }

    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Comment>() {
            override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
                return oldItem == newItem
            }

        }
    }

}