package com.demo.weather.ui.cityreport

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.demo.weather.viewmodel.CommonViewModel

class CityReportViewModel : CommonViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}