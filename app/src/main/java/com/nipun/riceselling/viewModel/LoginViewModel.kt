package com.nipun.riceselling.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nipun.riceselling.model.LoginModel
import com.nipun.riceselling.repository.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel(var loginRepository: LoginRepository): ViewModel() {
    var loginResponseData = MutableLiveData<LoginModel>()

    fun loginApi(): LiveData<LoginModel> {
        viewModelScope.launch {
            val uploadResponse = loginRepository.hitLoginApi()
            loginResponseData = uploadResponse
        }
        return loginResponseData
    }
}