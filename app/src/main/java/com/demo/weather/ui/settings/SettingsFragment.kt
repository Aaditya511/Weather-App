package com.demo.weather.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.demo.weather.R
import com.demo.weather.databinding.FragmentSettingsBinding
import com.demo.weather.utils.Constants

class SettingsFragment : Fragment() {

    private lateinit var settingViewModel: SettingsViewModel
    private var _binding: FragmentSettingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        settingViewModel =
            ViewModelProvider(this).get(SettingsViewModel::class.java)

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tempUnit = settingViewModel.getTempUnit(requireContext())

        if (tempUnit == Constants.TemperatureUnits.Fahrenheit) {
            binding.rgTempUnitsFahrenheit.isChecked = true
        } else {
            binding.rgTempUnitsCelsius.isChecked = true
        }

        binding.rgTempUnits.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.rgTempUnitsCelsius) {
                settingViewModel.saveTempUnit(requireContext(), Constants.TemperatureUnits.Celsius)
            } else if (checkedId == R.id.rgTempUnitsFahrenheit) {
                settingViewModel.saveTempUnit(requireContext(), Constants.TemperatureUnits.Fahrenheit)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}