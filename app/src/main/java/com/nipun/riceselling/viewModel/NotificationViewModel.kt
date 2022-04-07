package com.nipun.riceselling.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nipun.riceselling.model.NotificationListModel
import com.nipun.riceselling.repository.NotificationListRepository
import kotlinx.coroutines.launch

class NotificationViewModel(var notificationListRepository: NotificationListRepository) :
    ViewModel() {
    var loginResponseData = MutableLiveData<NotificationListModel>()

    fun notificationApi(token: String, context: Context): LiveData<NotificationListModel> {
        viewModelScope.launch {
            val uploadResponse = notificationListRepository.hitNotificationApi(token, context)
            loginResponseData = uploadResponse
        }
        return loginResponseData
    }
}