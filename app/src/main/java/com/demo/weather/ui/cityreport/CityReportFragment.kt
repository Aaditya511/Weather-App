package com.demo.weather.ui.cityreport

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.demo.weather.databinding.FragmentCityWeatherReportBinding

class CityReportFragment : Fragment() {

    private lateinit var homeViewModel: CityReportViewModel
    private var _binding: FragmentCityWeatherReportBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(CityReportViewModel::class.java)

        _binding = FragmentCityWeatherReportBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}