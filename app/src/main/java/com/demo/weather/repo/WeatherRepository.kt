package com.demo.weather.repo

import com.demo.weather.data.oneapiresponse.OneAPIResponse
import com.demo.weather.network.OpenWeatherAPIService
import retrofit2.Callback

class WeatherRepository(private val openWeatherAPIService: OpenWeatherAPIService) {

    fun getCurrentCityData(
        lat: Double,
        lon: Double,
        appId: String,
        unit: String,
        apiResponseCallback: Callback<OneAPIResponse>
    ) {
        openWeatherAPIService
            .getCurrentWeatherData(lat, lon, appId, unit)
            .enqueue(apiResponseCallback)
    }

    fun getCurrentCityForecastData(
        lat: Double,
        lon: Double,
        appId: String,
        unit: String,
        apiResponseCallback: Callback<OneAPIResponse>
    ) {
        openWeatherAPIService
            .getCurrentCityForecastData(lat, lon, appId, unit)
            .enqueue(apiResponseCallback)
    }
}