package com.dapascript.wings.data.remote.network

import com.dapascript.wings.data.remote.model.LoginResponse
import com.dapascript.wings.data.remote.model.ProductResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("login.php")
    suspend fun login(
        @Field("user") username: String,
        @Field("password") password: String
    ): LoginResponse

    @GET("product.php")
    suspend fun getProduct(): ProductResponse
}