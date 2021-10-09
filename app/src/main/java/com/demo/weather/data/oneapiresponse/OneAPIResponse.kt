package com.demo.weather.data.oneapiresponse

data class OneAPIResponse(
    val current: Current,
    val daily: List<Daily>,
    val lat: String,
    val lon: String
)