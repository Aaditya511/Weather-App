package com.demo.weather.viewmodel

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.weather.utils.Constants
import com.demo.weather.utils.Constants.SharedPrefName


open class CommonViewModel : ViewModel() {

    val error = MutableLiveData<String>()

    fun getAppId() = "ec2547a88e35aeae78b67f89aab8003d"

    fun saveUserName(context: Context, name: String) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(SharedPrefName, MODE_PRIVATE)
        val myEdit = sharedPreferences.edit()
        myEdit.putString("name", name)
        myEdit.apply()
    }

    fun getUsername(context: Context): String {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(SharedPrefName, MODE_PRIVATE)
        return sharedPreferences.getString("name", "") ?: ""
    }

    fun saveTempUnit(context: Context, unit: String) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(SharedPrefName, MODE_PRIVATE)
        val myEdit = sharedPreferences.edit()
        myEdit.putString("unit", unit)
        myEdit.apply()
    }

    fun getTempUnit(context: Context): String {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(SharedPrefName, MODE_PRIVATE)
        return sharedPreferences.getString("unit", Constants.TemperatureUnits.Celsius)
            ?: Constants.TemperatureUnits.Celsius
    }
}