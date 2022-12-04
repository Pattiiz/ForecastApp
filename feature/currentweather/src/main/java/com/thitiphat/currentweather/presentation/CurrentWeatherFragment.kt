package com.thitiphat.currentweather.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.thitiphat.core.util.TemperatureConversionUtil
import com.thitiphat.currentweather.R
import com.thitiphat.currentweather.constant.Constant
import com.thitiphat.currentweather.databinding.FragmentCurrentWeatherBinding
import com.thitiphat.data.currentweather.model.CurrentWeatherResponseModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrentWeatherFragment : Fragment() {

    private val binding: FragmentCurrentWeatherBinding by lazy {
        FragmentCurrentWeatherBinding.inflate(
            layoutInflater
        )
    }
    private val viewModel: CurrentWeatherViewModel by viewModel<CurrentWeatherViewModel>()

    private var isCelsius = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        initObserver()
        viewModel.getCurrentWeather(Constant.DEFAULT_LOCATION)
    }

    private fun initView() {
        binding.apply {
            ivRefresh.setOnClickListener {
                viewModel.getCurrentWeather(Constant.DEFAULT_LOCATION)
            }
            btnSeeMore.setOnClickListener {
                viewModel.getForecast(
                    viewModel.currentWeather.value?.coord?.lat.toString(),
                    viewModel.currentWeather.value?.coord?.lon.toString()
                )
            }
            btnChangeTempUnit.setOnClickListener {
                if (isCelsius) {
                    tvTemp.text =
                        viewModel.currentWeather.value?.main?.temp.toString()
                    tvTempUnit.text = getString(R.string.fahrenheit)
                    isCelsius = false
                } else {
                    tvTemp.text =
                        TemperatureConversionUtil.fahrenheitToCelsius(viewModel.currentWeather.value?.main?.temp.toString())
                    tvTempUnit.text = getString(R.string.celsius)
                    isCelsius = true
                }
            }
        }
    }

    private fun initObserver() {
        viewModel.currentWeather.observe(viewLifecycleOwner) {
            setCurrentWeather(it)
        }
        viewModel.forecast.observe(viewLifecycleOwner) {
            CurrentWeatherFragmentDirections.goToAllDayForecast(
                it,
                viewModel.currentWeather.value
            ).let { nav ->
                findNavController().navigate(nav)
            }
        }
    }

    private fun setCurrentWeather(data: CurrentWeatherResponseModel) {
        binding.apply {
            tvLocation.text = data.name
            tvTemp.text = data.main?.temp.toString()
            tvWeather.text = data.weather?.first()?.main
            tvHumidity.text = data.main?.humidity.toString() + getString(R.string.percent)
        }
    }

}