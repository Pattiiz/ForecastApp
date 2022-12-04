package com.thitiphat.forecast.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.thitiphat.forecast.R
import androidx.navigation.fragment.navArgs
import com.thitiphat.forecast.databinding.FragmentForecastBinding
import com.thitiphat.core.util.TemperatureConversionUtil
import com.thitiphat.core.util.TimeConversionUtil
import com.thitiphat.data.forecast.model.ForecastModel
import com.thitiphat.data.forecast.model.ForecastResponseModel
import java.text.SimpleDateFormat
import java.util.Calendar

class ForecastFragment : Fragment() {

    private val binding: FragmentForecastBinding by lazy {
        FragmentForecastBinding.inflate(
            layoutInflater
        )
    }

    private val args: ForecastFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.composeView.apply {
            setContent {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                AllDayForecastScreen(args.forecastData, args.cityName)

            }
        }
        return binding.root
    }

    @Composable
    fun AllDayForecastScreen(data: ForecastResponseModel, cityName: String) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = cityName,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp),
                fontSize = 42.sp
            )
            Text(
                text = SimpleDateFormat(stringResource(id = R.string.dd_MM_yyy_HH_mm)).format(
                    Calendar.getInstance().time
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 8.dp),
                fontSize = 26.sp
            )
            AllDayForecast(data = data.list)
        }
    }

    @Composable
    fun AllDayForecast(data: List<ForecastModel>) {
        val isCelsius = remember { mutableStateOf(false) }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                items(data) { eachItem ->
                    EachHours(eachItem, isCelsius.value)
                }
            }
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .padding(horizontal = 32.dp),
                onClick = { isCelsius.value = !isCelsius.value }
            ) {
                Text(
                    text = if (isCelsius.value) stringResource(id = R.string.forecast_change_to_fahrenheit)
                    else stringResource(id = R.string.forecast_change_to_celsius),
                )
            }
        }

    }

    @Composable
    fun EachHours(data: ForecastModel, isCelsius: Boolean) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(start = 16.dp)
                .background(color = Color(236, 239, 241), RoundedCornerShape(4.dp))
                .padding(16.dp)
                .width(140.dp)
        ) {
            data.dt?.let {
                Text(
                    text = TimeConversionUtil.unixTimeToGmt(it)
                )
            }

            Text(
                modifier = Modifier.padding(top = 16.dp),
                fontSize = 20.sp,
                text = stringResource(id = R.string.temp) + if (isCelsius) TemperatureConversionUtil.fahrenheitToCelsius(
                    data.main?.temp.toString()
                ) + stringResource(id = R.string.celsius) else data.main?.temp.toString() + stringResource(
                    id = R.string.fahrenheit
                )
            )
            Text(
                modifier = Modifier.padding(top = 16.dp),
                fontSize = 20.sp,
                text = stringResource(id = R.string.humidity) + data.main?.humidity + stringResource(
                    id = R.string.percent
                )
            )
        }
    }
}