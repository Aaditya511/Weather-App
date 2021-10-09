package com.demo.weather.ui.report

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.weather.data.oneapiresponse.Daily
import com.demo.weather.databinding.ItemWheatherInfoBinding

class ReportsAdapter(private val dataList: List<Daily>, private val tempUnit: String) : RecyclerView.Adapter<ReportsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportsViewHolder {
        val binding =
            ItemWheatherInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReportsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReportsViewHolder, position: Int) {
        holder.bindData(dataList[position], tempUnit)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}