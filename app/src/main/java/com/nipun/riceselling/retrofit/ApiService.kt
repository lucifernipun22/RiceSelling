package com.nipun.riceselling.retrofit

import com.google.gson.JsonObject
import com.nipun.riceselling.model.*
import com.nipun.riceselling.utils.Constants
import retrofit2.Call
import retrofit2.http.*

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
    fun sendMessage(
        @Header("Authorization") token: String,
        @Body jsonObject: JsonObject
    ): Call<ForgetPasswordModel>

    @GET(Constants.API_NOTIFICATION_LIST)
    fun getNotificationList(@Header("Authorization") token: String): Call<NotificationListModel>

    @GET(Constants.API_CATEGORY_LIST)
    fun getCategoryList(): Call<CategoryListModel>

    @GET(Constants.API_POPULAR_PRODUCT)
    fun getPopularList(): Call<PopularProductsModel>

    @GET("admin/api/v1/categories/products/{id}")
    fun getCategoryProductList(@Path("id") id: String): Call<CategoryProductModel>

    @GET("admin/api/v1/products/details/{id}")
    fun getProductDetail(@Path("id") id: String): Call<ProductDetailModel>

    @GET("admin/api/v1/coupon/apply")
    fun applyPromoCode(@Query("code") id: String,@Header("Authorization") token: String): Call<ApplyCodeModel>

    @GET(Constants.API_FETCH_ADDRESS)
    fun addressFetch(@Header("Authorization") token: String): Call<AddressModel>

    @POST(Constants.API_ADD_ADDRESS)
    fun addAddress(
        @Header("Authorization") token: String,
        @Body jsonObject: JsonObject
    ): Call<ForgetPasswordModel>

    @POST(Constants.API_PLACE_ORDER)
    fun placeOrder(
        @Header("Authorization") token: String,
        @Body jsonObject: JsonObject
    ): Call<PlaceOrderModel>
}