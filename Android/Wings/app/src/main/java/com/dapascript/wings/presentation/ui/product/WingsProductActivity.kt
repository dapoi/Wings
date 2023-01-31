package com.dapascript.wings.presentation.ui.product

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dapascript.wings.databinding.ActivityWingsProductBinding
import com.dapascript.wings.presentation.adapter.ProductAdapter
import com.dapascript.wings.presentation.ui.product.CheckoutProductActivity.Companion.EXTRA_PRODUCT
import com.dapascript.wings.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WingsProductActivity : AppCompatActivity() {

    private val binding: ActivityWingsProductBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityWingsProductBinding.inflate(layoutInflater)
    }
    private val productViewModel: WingsProductViewModel by viewModels()
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        productAdapter = ProductAdapter()
        binding.rvProduct.apply {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(this@WingsProductActivity)
            setHasFixedSize(true)
            productAdapter.onClick = {
                val intent = Intent(this@WingsProductActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_PRODUCT, it)
                startActivity(intent)
            }
        }

        binding.btnCheckout.apply {
            val selectedItem = productAdapter.getSelectedItem()
            setOnClickListener {
                val intent = Intent(this@WingsProductActivity, CheckoutProductActivity::class.java)
                intent.putExtra(CheckoutProductActivity.EXTRA_PRODUCT, selectedItem.toTypedArray())
                startActivity(intent)
            }
        }

        productViewModel.getProduct().observe(this) { response ->
            when (response) {
                is Resource.Loading -> stateLoading(true)
                is Resource.Success -> {
                    stateLoading(false)
                    productAdapter.setListProduct(response.data!!)
                }
                is Resource.Error -> {
                    stateLoading(false)
                    Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun stateLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }
}