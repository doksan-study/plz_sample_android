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
    ): View {
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

        binding.lifecycleOwner = this
        // Specify the current fragment as the lifecycle owner
        // `LiveData`는 데이터 변경을 구독하는 관찰자의 수면 주기를 알고 있다.
        // Binding 클래스와 함께 `LiveData` 객체를 사용하려면, 수명 주기 소유자를 지정하여 `LiveData` 객체의 범위를 정의해야한다.
        /**
         * binding.lifecycleOwner used for observing LiveData with data binding.
         * Kind of android:text=@{viewModel.text} where val text:LiveData<String>.
         * View will observe text changes at runtime.
         */
        binding.productViewModel = viewModel
//        subscribeToModel(viewModel)
    }

    private fun subscribeToModel(viewModel: ProductViewModel) {
        viewModel.product.observe(viewLifecycleOwner) { product ->
            if (product != null) {
                binding.productViewModel = viewModel
            } else {

            }
        }
    }

}