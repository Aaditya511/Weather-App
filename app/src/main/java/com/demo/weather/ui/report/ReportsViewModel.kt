package com.demo.weather.ui.report

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.demo.weather.data.oneapiresponse.Daily
import com.demo.weather.data.oneapiresponse.OneAPIResponse
import com.demo.weather.network.NetworkManger
import com.demo.weather.repo.WeatherRepository
import com.demo.weather.viewmodel.CommonViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReportsViewModel : CommonViewModel() {

    val repo = WeatherRepository(NetworkManger.getInstance())

    val reports = MutableLiveData<List<Daily>?>()

    fun getReportsData(
        context: Context,
        lat: Double,
        lon: Double
    ) {

        repo.getCurrentCityForecastData(lat, lon, getAppId(), getTempUnit(context), object :
            Callback<OneAPIResponse> {
            override fun onResponse(
                call: Call<OneAPIResponse>,
                response: Response<OneAPIResponse>
            ) {
                if (response.isSuccessful) {
                    reports.value = response.body()?.daily
                } else {
                    error.value = response.message()
                }
            }

            override fun onFailure(call: Call<OneAPIResponse>, t: Throwable) {
                error.value = t.message
            }
        })
    }
}