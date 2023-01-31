package com.dapascript.wings.presentation.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dapascript.wings.data.remote.model.LoginResponse
import com.dapascript.wings.data.repository.WingsRepository
import com.dapascript.wings.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val wingsRepository: WingsRepository
) : ViewModel() {

    fun login(username: String, password: String): LiveData<Resource<LoginResponse>> {
        return wingsRepository.login(username, password).asLiveData()
    }
}