package com.demo.weather.ui.current

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.demo.weather.data.oneapiresponse.Current
import com.demo.weather.data.oneapiresponse.OneAPIResponse
import com.demo.weather.network.NetworkManger
import com.demo.weather.repo.WeatherRepository
import com.demo.weather.viewmodel.CommonViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrentViewModel : CommonViewModel() {

    val repo = WeatherRepository(NetworkManger.getInstance())

    val currentData = MutableLiveData<Current?>()

    fun getCurrentCityData(
        context: Context,
        lat: Double,
        lon: Double
    ) {

        repo.getCurrentCityData(
            lat,
            lon,
            getAppId(),
            getTempUnit(context),
            object : Callback<OneAPIResponse> {
                override fun onResponse(
                    call: Call<OneAPIResponse>,
                    response: Response<OneAPIResponse>
                ) {
                    if (response.isSuccessful) {
                        currentData.value = response.body()?.current
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