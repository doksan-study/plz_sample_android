package com.example.viewmodelbasicsimple.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelbasicsimple.R
import com.example.viewmodelbasicsimple.databinding.FragmentProductListBinding
import com.example.viewmodelbasicsimple.model.Product
import com.example.viewmodelbasicsimple.viewmodel.ProductListViewModel

class ProductListFragment : Fragment() {

    private lateinit var binding: FragmentProductListBinding
    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_product_list, container, false)

        productAdapter = ProductAdapter {product -> productClickCallback(product) }
        binding.productList.adapter = productAdapter
        return binding.root
    }

    private fun productClickCallback(product: Product) {
        /** While your activity is in the STARTED lifecycle state or higher, fragments can be added, replaced, or removed. */
        if(lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)){
            (requireActivity() as MainActivity).show(product)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this).get(ProductListViewModel::class.java)
        binding.productSearchBtn.setOnClickListener { _ ->
            // 검색
            val searchText = binding.productSearchBox.text.toString().trimEnd()
            if (searchText.isNullOrBlank())
                return@setOnClickListener
            // todo: 페이지네이션
            viewModel.searchProducts(1, 10, searchText)
        }

        subscribeUi(viewModel.products)
    }

    private fun subscribeUi(liveData: LiveData<List<Product>>) {
        // Update the List when the data changes
        liveData.observe(viewLifecycleOwner) { products ->
            if (products != null) {
//                binding.isLoading = false
                binding.loadingTv.isVisible = false
                productAdapter.submitList(products)
            } else {
//                binding.isLoading = true
                binding.loadingTv.isVisible = true
            }

            // espresso does not know how to wait for data binding's loop so we execute changes
            // sync.
            binding.executePendingBindings()
        }
    }

}