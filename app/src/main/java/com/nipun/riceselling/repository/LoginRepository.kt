package com.nipun.riceselling.repository

import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import com.nipun.riceselling.model.LoginModel
import com.nipun.riceselling.utils.BaseActivity
import com.nipun.riceselling.utils.Constants
import com.nipun.riceselling.utils.Utiles
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository(var baseActivity: BaseActivity){

    private var loginResponse = MutableLiveData<LoginModel>()

     fun hitLoginApi(): MutableLiveData<LoginModel> {
        if (Utiles.Utiles.internetChack()) {
            val jsonObject = JsonObject()
            jsonObject.addProperty("email", "nipunj270@gmail.com")
            jsonObject.addProperty("email_or_phone", "nipunj270@gmail.com")
            jsonObject.addProperty("password", "nipunj270@")
            val call: Call<LoginModel> = baseActivity.apiService!!.getLogin(jsonObject)
            baseActivity.startProgress()
            call.enqueue(object : Callback<LoginModel?> {
                override fun onResponse(
                    call: Call<LoginModel?>,
                    response: Response<LoginModel?>
                ) {
                    baseActivity.stopProgress()
                    if (response.isSuccessful) {
                        if (response.code() == Constants.STATUS_OK) {
                            if (response.body() != null) {
                                val body: LoginModel? = response.body()
                                loginResponse.value = body
                            }
                        } else {
                            baseActivity.onAPiFailure(response.code(), null, response, call, this)
                        }
                    } else {
                        baseActivity.onAPiFailure(response.code(), null, response, call, this)
                    }
                }

                override fun onFailure(call: Call<LoginModel?>, t: Throwable) {
                    baseActivity.stopProgress()
                    baseActivity.onAPiFailure(0, t, null, call, this)
                }
            })
        } else {
            baseActivity.noInternetAlertBox("No Internet Connection")
        }
        return loginResponse
    }

}