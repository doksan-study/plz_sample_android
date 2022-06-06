package com.example.viewmodelbasicsimple.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.viewmodelbasicsimple.R
import com.example.viewmodelbasicsimple.model.Product

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = ProductListFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, fragment).commit()
    }

    /** Shows the product detail fragment  */
    fun show(product: Product) {
        val productFragment = ProductFragment(product.id)

        /** You can keep a record of these changes in a back stack that is managed by the activity, allowing the changes to be reversed. */
        supportFragmentManager.beginTransaction()
            .addToBackStack("product")
            .replace(R.id.fragment_container, productFragment, null)
            .commit()
    }
}