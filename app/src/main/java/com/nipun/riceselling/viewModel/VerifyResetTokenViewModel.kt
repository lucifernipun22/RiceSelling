package com.nipun.riceselling.viewModel

import android.content.Context
import androidx.lifecycle.*
import com.nipun.riceselling.model.ForgetPasswordModel
import com.nipun.riceselling.repository.VerifyResetTokenRepository
import kotlinx.coroutines.launch

class VerifyResetTokenViewModel(var verifyResetTokenRepository: VerifyResetTokenRepository): ViewModel() {
    var loginResponseData = MutableLiveData<ForgetPasswordModel>()

    fun verifyTokenApi(editEmail: String, token: String,context: Context): LiveData<ForgetPasswordModel> {
        viewModelScope.launch {
            val uploadResponse = verifyResetTokenRepository.hitVerifyApi(editEmail,token,context)
            loginResponseData = uploadResponse
        }
        return loginResponseData
    }
}