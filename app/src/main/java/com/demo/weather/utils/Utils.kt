package com.demo.weather.utils

import com.demo.weather.R
import com.demo.weather.data.oneapiresponse.Weather
import com.demo.weather.databinding.ItemWheatherInfoBinding
import com.squareup.picasso.Picasso

fun getImageUrl(icon: String?) = "https://openweathermap.org/img/wn/$icon@4x.png"

fun ItemWheatherInfoBinding.setData(
    humidity: String,
    pressure: String,
    windDeg: String,
    windSpeed: String,
    weatherInfo: Weather?,
    temp: String,
    tempUnit: String,
    tempMin: String? = null,
    tempMax: String? = null,
) {

    val context = root.context
    tvHumidity.text = context.getString(R.string.humidity, humidity)
    tvPressure.text = context.getString(R.string.pressure, pressure)
    tvTemp.text = context.getString(R.string.temp_main, temp)
    tvWindSpeed.text = context.getString(R.string.wind_degree, windDeg)
    tvWindDegree.text = context.getString(R.string.wind_speed, windSpeed)
    tvWeatherInfo.text = context.getString(
        R.string.weather_info,
        weatherInfo?.main,
        weatherInfo?.description
    )
    Picasso.get().load(getImageUrl(weatherInfo?.icon)).into(ivWeather)
    if (tempUnit == Constants.TemperatureUnits.Fahrenheit) {
        tvTempUnit.text = "F"

    } else {
        tvTempUnit.text = "C"
    }
    if (tempMin != null && tempMax != null) {
        tvTempMinMax.text = context.getString(
            R.string.temp_min_max,
            tempMin,
            tempMax
        )
    }
}
