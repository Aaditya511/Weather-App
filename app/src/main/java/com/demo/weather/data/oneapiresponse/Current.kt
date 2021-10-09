package com.demo.weather.data.oneapiresponse

data class Current(
    val dt: String,
    val humidity: String,
    val pressure: String,
    val temp: String,
    val visibility: String,
    val weather: List<Weather>,
    val wind_deg: String,
    val wind_speed: String
)