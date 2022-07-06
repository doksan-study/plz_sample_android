package com.example.viewmodelbasicsimple.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelbasicsimple.R
import com.example.viewmodelbasicsimple.databinding.FragmentProductBinding
import com.example.viewmodelbasicsimple.model.Comment
import com.example.viewmodelbasicsimple.viewmodel.CommentListViewModel
import com.example.viewmodelbasicsimple.viewmodel.ProductViewModel

class ProductFragment(private val productId: Int) : Fragment() {

    private lateinit var binding: FragmentProductBinding

    private lateinit var productViewModel: ProductViewModel
    private lateinit var productViewModelFactory: ProductViewModel.ViewModelFactory

    private lateinit var commentListViewModelFactory: CommentListViewModel.ViewModelFactory
    private lateinit var commentListViewModel: CommentListViewModel
    private lateinit var commentAdapter: CommentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_product,
            container,
            false
        )

        commentAdapter = CommentAdapter()
        binding.commentList.adapter = commentAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**
         * binding.lifecycleOwner used for observing LiveData with data binding.
         * Kind of android:text=@{viewModel.text} where val text:LiveData<String>.
         * View will observe text changes at runtime.
         */
        binding.lifecycleOwner = this

        /** Init `ProductViewModel` */
        productViewModelFactory = ProductViewModel.ViewModelFactory(productId)
        productViewModel = ViewModelProvider(this, productViewModelFactory)
            .get(ProductViewModel::class.java)
        binding.productViewModel = productViewModel

        /** Init `CommentListViewModel` */
        commentListViewModelFactory = CommentListViewModel.ViewModelFactory(productId)
        commentListViewModel = ViewModelProvider(this, commentListViewModelFactory).get(CommentListViewModel::class.java)
        subscribeToComments(commentListViewModel.comments)
    }

    private fun subscribeToComments(comments: LiveData<List<Comment>>) {
        comments.observe(viewLifecycleOwner) { it ->
            if (it != null) {
                commentAdapter.submitList(it)
            } else {

            }
            binding.executePendingBindings()
        }
    }

}