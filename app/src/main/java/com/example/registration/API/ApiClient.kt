package com.example.registration.API

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.registration.CodeHiveRegApplication
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    val client = OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor(CodeHiveRegApplication.appContext))
        .build()

    var retrofit = Retrofit.Builder()
        .baseUrl("http://13.244.243.129")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
    fun <T> buildApiClient(apiInterface: Class<T>): T {
        return retrofit.create(apiInterface)
    }

}