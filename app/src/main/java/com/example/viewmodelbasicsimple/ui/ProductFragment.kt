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
import com.example.viewmodelbasicsimple.model.Product
import com.example.viewmodelbasicsimple.viewmodel.ProductViewModel

class ProductFragment(private val productId: Int) : Fragment() {

    private lateinit var binding: FragmentProductBinding
    private lateinit var viewModel: ProductViewModel
    private lateinit var viewModelFactory: ProductViewModel.ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_product,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelFactory = ProductViewModel.ViewModelFactory(productId)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ProductViewModel::class.java)

        subscribeUi(viewModel)
    }

    private fun subscribeUi(viewModel: ProductViewModel) {

    }

}