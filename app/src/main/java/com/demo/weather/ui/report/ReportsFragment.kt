package com.demo.weather.ui.report

import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.demo.weather.BaseFragment
import com.demo.weather.databinding.FragmentReportsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class ReportsFragment : BaseFragment() {

    private lateinit var viewModel: ReportsViewModel
    private var _binding: FragmentReportsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this).get(ReportsViewModel::class.java)

        _binding = FragmentReportsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerObserver()
        requestLocationPermissionIfRequired()

    }

    private fun registerObserver() {
        viewModel.reports.observe(viewLifecycleOwner) {
            it?.let {
                binding.rvReports.apply {
                    hideProgress()
                    val tempUnit = viewModel.getTempUnit(requireContext())
                    adapter = ReportsAdapter(it, tempUnit)
                }
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
                    viewModel.getReportsData(requireContext(), it.latitude, it.longitude)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}