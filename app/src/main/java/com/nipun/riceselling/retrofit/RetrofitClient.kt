package com.nipun.riceselling.retrofit

import android.content.Context
import com.google.gson.Gson
import com.nipun.riceselling.utils.SingletonHolder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient private constructor(context: Context) {
    var retrofit: Retrofit? = null

    companion object : SingletonHolder<RetrofitClient, Context>(::RetrofitClient)

    fun getClient(baseUrl: String?): Retrofit? {
        /*val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl!!)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
        return retrofit*/

        if(retrofit== null){
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(200, TimeUnit.SECONDS)
                .readTimeout(200, TimeUnit.SECONDS)
                .build()
            retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}