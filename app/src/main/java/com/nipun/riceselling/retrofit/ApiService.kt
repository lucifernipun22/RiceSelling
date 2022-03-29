package com.nipun.riceselling.retrofit

import com.google.gson.JsonObject
import com.nipun.riceselling.model.LoginModel
import com.nipun.riceselling.utils.Constants
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST(Constants.API_LOGIN)
    fun getLogin(@Body jsonObject: JsonObject): Call<LoginModel>
}