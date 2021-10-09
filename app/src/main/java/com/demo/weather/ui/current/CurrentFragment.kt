package com.demo.weather.ui.current

import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.demo.weather.BaseFragment
import com.demo.weather.databinding.FragmentCurrentBinding
import com.demo.weather.utils.setData
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class CurrentFragment : BaseFragment() {

    private lateinit var currentViewModel: CurrentViewModel
    private var _binding: FragmentCurrentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        currentViewModel =
            ViewModelProvider(this).get(CurrentViewModel::class.java)

        _binding = FragmentCurrentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerObserver()
        requestLocationPermissionIfRequired()
    }

    private fun registerObserver() {
        currentViewModel.currentData.observe(viewLifecycleOwner) {
            hideProgress()
            it?.let {
                val temp = it.temp
                val tempUnit = currentViewModel.getTempUnit(requireContext())
                binding.inReport.setData(
                    it.humidity, it.pressure, it.wind_deg, it.wind_speed, it.weather.getOrNull(0),
                    temp, tempUnit, temp, temp
                )

            }
        }
    }

    override fun onLocationPermissionGranted() {
        super.onLocationPermissionGranted()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                // Got last known location. In some rare situations this can be null.
                location?.let {
                    showProgress()
                    currentViewModel.getCurrentCityData(requireContext(), it.latitude, it.longitude)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}