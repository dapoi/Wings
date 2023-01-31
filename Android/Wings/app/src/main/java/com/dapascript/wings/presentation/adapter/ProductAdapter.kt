package com.dapascript.wings.presentation.adapter

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dapascript.wings.data.remote.model.ProductItem
import com.dapascript.wings.databinding.ItemListProductBinding

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private val listProduct = ArrayList<ProductItem>()
    private val selectedItem = HashSet<ProductItem>()

    var onClick: ((ProductItem) -> Unit)? = null

    fun setListProduct(listProduct: List<ProductItem>) {
        this.listProduct.clear()
        this.listProduct.addAll(listProduct)
        notifyDataSetChanged()
    }

    fun getSelectedItem(): HashSet<ProductItem> {
        return selectedItem
    }

    inner class ProductViewHolder(
        binding: ItemListProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val ivCart = binding.ivCart
        val tvProductName = binding.tvProductName
        val tvProductDiscount = binding.tvProductDiscount
        val tvProductPrice = binding.tvProductPrice
        val btnAddToCart = binding.btnCheckout
        val cardProduct = binding.cardProduct
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemListProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = listProduct[position]
        holder.apply {
            tvProductName.text = product.productName
            tvProductPrice.text = "${product.currency} ${product.price}"
            if (product.discount != null) {
                tvProductDiscount.visibility = View.VISIBLE
                tvProductDiscount.text = "${product.currency} ${product.discount}"
                tvProductDiscount.paintFlags =
                    tvProductDiscount.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }

            cardProduct.isActivated = selectedItem.contains(product)
            btnAddToCart.setOnClickListener {
                if (cardProduct.isActivated) {
                    selectedItem.remove(product)
                    ivCart.visibility = View.GONE
                    cardProduct.isActivated = false
                } else {
                    selectedItem.add(product)
                    ivCart.visibility = View.VISIBLE
                    cardProduct.isActivated = true
                }
            }

            cardProduct.setOnClickListener {
                onClick?.invoke(product)
            }
        }
    }

    override fun getItemCount(): Int = listProduct.size
}