package com.nipun.riceselling.retrofit

import com.google.gson.JsonObject
import com.nipun.riceselling.model.*
import com.nipun.riceselling.utils.Constants
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
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

    @GET(Constants.API_COUPON_LIST)
    fun getCouponList(@Header("Authorization") token: String): Call<CouponModel>

    @GET(Constants.API_USER_INFO)
    fun getUserInfo(@Header("Authorization") token: String): Call<UserInfoModel>

    @GET(Constants.API_CONFIG)
    fun getConfig(): Call<ConfigModel>

    @GET(Constants.API_BANNER)
    fun getBanner(): Call<BannerModel>

    @GET(Constants.API_TIME_SLOT)
    fun getTimeSlot(): Call<TimeSlotModel>

    @GET(Constants.API_GET_MESSAGE)
    fun getMessage(@Header("Authorization") token: String): Call<GetMessagesModel>

    @POST(Constants.API_SEND_MESSAGE)
    fun sendMessage(@Header("Authorization") token: String,@Body jsonObject: JsonObject): Call<ForgetPasswordModel>

    @GET(Constants.API_NOTIFICATION_LIST)
    fun getNotificationList(@Header("Authorization") token: String): Call<NotificationListModel>
}