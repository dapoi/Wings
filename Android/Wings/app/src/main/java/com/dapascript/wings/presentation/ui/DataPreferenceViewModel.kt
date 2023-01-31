package com.dapascript.wings.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dapascript.wings.data.pref.DataPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataPreferenceViewModel @Inject constructor(
    private val dataPreference: DataPreference
) : ViewModel() {

    fun saveHasLogin(hasLogin: Boolean) {
        viewModelScope.launch {
            dataPreference.saveHasLogin(hasLogin)
        }
    }

    val hasLogin: LiveData<Boolean> = dataPreference.getHasLogin().asLiveData()
}