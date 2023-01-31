package com.dapascript.wings.presentation.ui.product

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dapascript.wings.data.remote.model.ProductItem
import com.dapascript.wings.databinding.ActivityCheckoutProductBinding
import com.dapascript.wings.presentation.adapter.CheckoutProductAdapter

class CheckoutProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckoutProductBinding
    private val checkoutAdapter = CheckoutProductAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.apply {
            val productCheckout = intent.getParcelableArrayExtra(EXTRA_PRODUCT)
            with(rvProduct) {
                adapter = checkoutAdapter
                layoutManager = LinearLayoutManager(this@CheckoutProductActivity)
                setHasFixedSize(true)
                checkoutAdapter.setListProduct(productCheckout!!.toList() as List<ProductItem>)
            }

            tvTotalPrice.text = "Total Rp. ${checkoutAdapter.getTotalPrice()}"
        }
    }

    companion object {
        const val EXTRA_PRODUCT = "extra_product"
    }
}