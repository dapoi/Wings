package com.dapascript.wings.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dapascript.wings.data.remote.model.ProductItem
import com.dapascript.wings.databinding.ItemListCheckoutBinding

class CheckoutProductAdapter : RecyclerView.Adapter<CheckoutProductAdapter.CheckoutViewHolder>() {

    private val listProduct = ArrayList<ProductItem>()
    private var quantity = 1
    private var currentPrice = 0
    private var totalPrice = 0

    fun setListProduct(listProduct: List<ProductItem>) {
        this.listProduct.clear()
        this.listProduct.addAll(listProduct)
        notifyDataSetChanged()

    }

    fun getTotalPrice(): Int {
        var currentPrice = 0
        listProduct.forEach {
            currentPrice += it.price!!
        }
        return currentPrice
    }

    inner class CheckoutViewHolder(
        private val binding: ItemListCheckoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ProductItem) {
            with(binding) {
                tvProductName.text = data.productName
                tvProductPrice.text = "${data.currency} ${data.price}"
                tvPcs.text = data.unit
                etProductQuantity.setText(quantity.toString())

                // price change when etQuantity change
                etProductQuantity.setOnFocusChangeListener { _, hasFocus ->
                    if (!hasFocus) {
                        quantity = etProductQuantity.text.toString().toInt()
                        totalPrice = quantity * data.price!!
                        currentPrice += totalPrice
                        tvProductPrice.text = "${data.currency} $totalPrice"
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckoutViewHolder {
        return CheckoutViewHolder(
            ItemListCheckoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CheckoutViewHolder, position: Int) {
        holder.bind(listProduct[position])
    }

    override fun getItemCount(): Int = listProduct.size
}