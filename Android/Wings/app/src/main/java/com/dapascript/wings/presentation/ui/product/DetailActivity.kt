package com.dapascript.wings.presentation.ui.product

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dapascript.wings.data.remote.model.ProductItem
import com.dapascript.wings.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val product = intent.getParcelableExtra<ProductItem>(EXTRA_PRODUCT)
        binding.apply {
            product?.let {
                tvProductName.text = it.productName
                tvPrice.text = "Rp. ${it.price}"
                tvDimension.text = it.dimension
                tvUnit.text = it.unit
            }
        }
    }

    companion object {
        const val EXTRA_PRODUCT = "extra_product"
    }
}