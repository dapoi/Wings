package com.dapascript.wings.data.repository

import com.dapascript.wings.data.remote.model.LoginResponse
import com.dapascript.wings.data.remote.model.ProductItem
import com.dapascript.wings.data.remote.network.ApiService
import com.dapascript.wings.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WingsRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : WingsRepository {
    override fun login(username: String, password: String): Flow<Resource<LoginResponse>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = apiService.login(username, password)
                emit(Resource.Success(response))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getProduct(): Flow<Resource<List<ProductItem>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = apiService.getProduct().data
                if (response.isNullOrEmpty()) {
                    emit(Resource.Error("Data is empty"))
                } else {
                    emit(Resource.Success(response) as Resource<List<ProductItem>>)
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}