package com.nipun.riceselling.repository

import android.content.Context
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import com.nipun.riceselling.R
import com.nipun.riceselling.model.LoginModel
import com.nipun.riceselling.utils.BaseActivity
import com.nipun.riceselling.utils.Constants
import com.nipun.riceselling.utils.Utiles
import com.nipun.riceselling.utils.motionToast.MotionToast
import com.nipun.riceselling.utils.motionToast.MotionToastStyle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository(var baseActivity: BaseActivity){

    private var loginResponse = MutableLiveData<LoginModel>()

     fun hitLoginApi(editEmail: String, editPass: String, context: Context): MutableLiveData<LoginModel> {
        if (Utiles.Utiles.internetChack()) {
            val jsonObject = JsonObject()
            jsonObject.addProperty("email", editEmail)
            jsonObject.addProperty("email_or_phone", editEmail)
            jsonObject.addProperty("password", editPass)
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
                            MotionToast.createToast(baseActivity,
                                "Failed",
                                "Invalid Credentials!",
                                MotionToastStyle.ERROR,
                                MotionToast.GRAVITY_BOTTOM,
                                MotionToast.LONG_DURATION,
                                ResourcesCompat.getFont(context, R.font.helvetica_regular))
                            baseActivity.onAPiFailure(response.code(), null, response, call, this)
                        }
                    } else {
                        MotionToast.createToast(baseActivity,
                            "Failed",
                            "Invalid Credentials!",
                            MotionToastStyle.ERROR,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(context, R.font.helvetica_regular))
                        baseActivity.onAPiFailure(response.code(), null, response, call, this)
                    }
                }

                override fun onFailure(call: Call<LoginModel?>, t: Throwable) {
                    baseActivity.stopProgress()
                    MotionToast.createToast(baseActivity,
                        "Failed",
                        "Invalid Credentials!",
                        MotionToastStyle.ERROR,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(context, R.font.helvetica_regular))
                    baseActivity.onAPiFailure(0, t, null, call, this)
                }
            })
        } else {
            MotionToast.createToast(baseActivity,
                "Error",
                "No Internet Connection!",
                MotionToastStyle.NO_INTERNET,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(context, R.font.helvetica_regular))
        }
        return loginResponse
    }

}