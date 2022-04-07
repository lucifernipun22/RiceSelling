package com.nipun.riceselling.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nipun.riceselling.model.ConfigModel
import com.nipun.riceselling.repository.ConfigRepository
import kotlinx.coroutines.launch

class ConfigViewModel(var configRepository: ConfigRepository): ViewModel() {
    var loginResponseData = MutableLiveData<ConfigModel>()

    fun configApi( context: Context): LiveData<ConfigModel> {
        viewModelScope.launch {
            val uploadResponse = configRepository.hitConfigApi(context)
            loginResponseData = uploadResponse
        }
        return loginResponseData
    }
}