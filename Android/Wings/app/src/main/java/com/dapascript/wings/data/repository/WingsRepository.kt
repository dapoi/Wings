package com.dapascript.wings.data.repository

import com.dapascript.wings.data.remote.model.LoginResponse
import com.dapascript.wings.data.remote.model.ProductItem
import com.dapascript.wings.utils.Resource
import kotlinx.coroutines.flow.Flow

interface WingsRepository {
    fun login(username: String, password: String): Flow<Resource<LoginResponse>>
    fun getProduct(): Flow<Resource<List<ProductItem>>>
}