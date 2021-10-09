package com.demo.weather.network

import com.demo.weather.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NetworkManger {

    fun getInstance(): OpenWeatherAPIService = Retrofit.Builder()
        .baseUrl(Constants.BaseUrl)
        .client(OkHttpClient.Builder()
            .apply {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }

            .build())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(OpenWeatherAPIService::class.java)
}

