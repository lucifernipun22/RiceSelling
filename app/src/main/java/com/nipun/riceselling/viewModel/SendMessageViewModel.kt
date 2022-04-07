package com.nipun.riceselling.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nipun.riceselling.model.ForgetPasswordModel
import com.nipun.riceselling.repository.SendMessageRepository
import kotlinx.coroutines.launch

class SendMessageViewModel(var sendMessageRepository: SendMessageRepository) : ViewModel() {
    var loginResponseData = MutableLiveData<ForgetPasswordModel>()

    fun sendMessageApi(context: Context, token: String, message: String): LiveData<ForgetPasswordModel> {
        viewModelScope.launch {
            val uploadResponse = sendMessageRepository.hitSendMessageApi(token, context, message)
            loginResponseData = uploadResponse
        }
        return loginResponseData
    }
}