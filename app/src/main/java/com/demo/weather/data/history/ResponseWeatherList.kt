package com.demo.weather.data.history

data class ResponseWeatherList(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherDataList>,
    val message: String
)