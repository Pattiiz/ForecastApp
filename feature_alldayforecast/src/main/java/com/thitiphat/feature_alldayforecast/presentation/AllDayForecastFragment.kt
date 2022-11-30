package com.thitiphat.feature_alldayforecast.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.thitiphat.feature_alldayforecast.databinding.FragmentAllDayForecastBinding

class AllDayForecastFragment : Fragment() {

    private val binding: FragmentAllDayForecastBinding by lazy {
        FragmentAllDayForecastBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}