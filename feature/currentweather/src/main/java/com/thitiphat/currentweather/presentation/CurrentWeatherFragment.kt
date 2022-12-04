package com.thitiphat.currentweather.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.thitiphat.currentweather.constant.Constant
import com.thitiphat.currentweather.databinding.FragmentWeatherBinding
import com.thitiphat.data.currentweather.model.CurrentWeatherResponseModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrentWeatherFragment : Fragment() {

    private val binding: FragmentWeatherBinding by lazy {
        FragmentWeatherBinding.inflate(
            layoutInflater
        )
    }
    private val viewModel: CurrentWeatherViewModel by viewModel<CurrentWeatherViewModel>()

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
            tvSeeMore.setOnClickListener {
                viewModel.getForecast(
                    viewModel.currentWeather.value?.coord?.lat.toString(),
                    viewModel.currentWeather.value?.coord?.lon.toString()
                )
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
                viewModel.currentWeather.value?.name.orEmpty()
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
            tvHumidity.text = data.main?.humidity.toString()
        }
    }

}