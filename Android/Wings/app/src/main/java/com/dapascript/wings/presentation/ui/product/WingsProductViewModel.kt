package com.dapascript.wings.presentation.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dapascript.wings.data.remote.model.ProductItem
import com.dapascript.wings.data.repository.WingsRepository
import com.dapascript.wings.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WingsProductViewModel @Inject constructor(
    private val wingsRepository: WingsRepository
) : ViewModel() {

    fun getProduct(): LiveData<Resource<List<ProductItem>>> {
        return wingsRepository.getProduct().asLiveData()
    }
}