package com.demo.weather.data.history

data class WeatherDataList(
    val dt: Int,
    val temp: Temp,
    val pressure: Int,
    val humidity: Int,
    val weather: List<Weather>,
    val speed: Double,
    val deg: Int
)