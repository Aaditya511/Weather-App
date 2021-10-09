package com.demo.weather.ui.report

import androidx.recyclerview.widget.RecyclerView
import com.demo.weather.data.oneapiresponse.Daily
import com.demo.weather.databinding.ItemWheatherInfoBinding
import com.demo.weather.utils.setData

class ReportsViewHolder(val binding: ItemWheatherInfoBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindData(it: Daily, tempUnit: String) {

        val temp = it.temp
        binding.setData(
            it.humidity,
            it.pressure,
            it.wind_deg,
            it.wind_speed,
            it.weather.getOrNull(0),
            temp.day,
            tempUnit,
            temp.min,
            temp.max
        )
    }
}