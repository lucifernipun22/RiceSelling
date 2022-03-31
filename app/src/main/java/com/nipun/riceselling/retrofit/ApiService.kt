package com.nipun.riceselling.retrofit

import com.google.gson.JsonObject
import com.nipun.riceselling.model.ForgetPasswordModel
import com.nipun.riceselling.model.LoginModel
import com.nipun.riceselling.utils.Constants
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST(Constants.API_LOGIN)
    fun getLogin(@Body jsonObject: JsonObject): Call<LoginModel>

    @POST(Constants.API_FORGET_PASSWORD)
    fun getForgetPassword(@Body jsonObject: JsonObject): Call<ForgetPasswordModel>

    @POST(Constants.API_VERIFY_RESET_TOKEN)
    fun getVerifyResetToken(@Body jsonObject: JsonObject): Call<ForgetPasswordModel>

    @POST(Constants.API_RESET_PASSWORD)
    fun getResetPassword(@Body jsonObject: JsonObject): Call<ForgetPasswordModel>

    @POST(Constants.API_SIGN_UP)
    fun getSignUp(@Body jsonObject: JsonObject): Call<LoginModel>
}