package com.demo.weather.data.oneapiresponse

data class Daily(
    val humidity: String,
    val pressure: String,
    val temp: Temp,
    val weather: List<Weather>,
    val wind_deg: String,
    val wind_speed: String
)