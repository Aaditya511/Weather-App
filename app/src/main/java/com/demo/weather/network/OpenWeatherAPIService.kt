package com.demo.weather.network

import com.demo.weather.data.oneapiresponse.OneAPIResponse
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherAPIService {

    @GET("/forecast/daily?q={cityName}&cnt={cnt}&appid={appId}&units={unit}")
    fun getCityData(
        @Query("cityName") cityName: String,
        @Query("cnt") cnt: Int,
        @Query("appid") appid: String,
        @Query("unit") unit: String
    ): Call<JsonElement?>

    @GET("/data/2.5/onecall?exclude=minutely,hourly,daily,alerts")
    fun getCurrentWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appId: String,
        @Query("units") unit: String
    ): Call<OneAPIResponse>

    @GET("/data/2.5/onecall?exclude=current,minutely,hourly,alerts")
    fun getCurrentCityForecastData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appId: String,
        @Query("units") unit: String
    ): Call<OneAPIResponse>

}
