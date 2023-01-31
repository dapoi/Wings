package com.dapascript.wings.data.remote.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductResponse(

    @Json(name = "data")
    val data: List<ProductItem?>? = null
) : Parcelable

@Parcelize
data class ProductItem(

    @Json(name = "unit")
    val unit: String? = null,

    @Json(name = "productCode")
    val productCode: String? = null,

    @Json(name = "price")
    val price: Int? = null,

    @Json(name = "discount")
    val discount: Int? = null,

    @Json(name = "currency")
    val currency: String? = null,

    @Json(name = "dimension")
    val dimension: String? = null,

    @Json(name = "productName")
    val productName: String? = null
) : Parcelable
