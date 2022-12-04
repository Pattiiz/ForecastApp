package com.thitiphat.domain.currentweather

import com.thitiphat.data.currentweather.model.CurrentWeatherResponseModel
import com.thitiphat.data.currentweather.model.LatLngModel
import com.thitiphat.data.currentweather.repository.CurrentWeatherRepository
import io.reactivex.rxjava3.core.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever
import retrofit2.Response
import java.io.IOException

class GetCurrentWeatherUseCaseTest {

    @Mock
    private lateinit var currentWeatherRepository: CurrentWeatherRepository

    private lateinit var getCurrentWeatherUseCase: GetCurrentWeatherUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getCurrentWeatherUseCase = GetCurrentWeatherUseCase(currentWeatherRepository)
    }

    @Test
    fun getData_success() {
        val response = listOf(
            LatLngModel(name = null, lat = null, lng = null, country = null)
        )
        val currentWeatherResponse = CurrentWeatherResponseModel(
            coord = null,
            weather = listOf(),
            base = null,
            main = null,
            dt = null,
            timezone = null,
            name = null
        )
        whenever(currentWeatherRepository.getLatLng(any())).thenReturn(
            Single.just(
                Response.success(
                    response
                )
            )
        )
        whenever(currentWeatherRepository.getCurrentWeather(any(), any())).thenReturn(
            Single.just(
                Response.success(currentWeatherResponse)
            )
        )


        getCurrentWeatherUseCase.invoke("") {}

        verify(currentWeatherRepository).getLatLng(any())
        verify(currentWeatherRepository).getCurrentWeather(any(), any())
        verifyNoMoreInteractions(currentWeatherRepository)
    }

    @Test
    fun getData_fail_step_1() {
        whenever(currentWeatherRepository.getLatLng(any())).thenReturn(Single.error(IOException()))

        getCurrentWeatherUseCase.invoke("") {}

        verify(currentWeatherRepository).getLatLng(any())
        verifyNoMoreInteractions(currentWeatherRepository)
    }

    @Test
    fun getData_fail_step_2() {
        val response = listOf(
            LatLngModel(name = null, lat = null, lng = null, country = null)
        )
        whenever(currentWeatherRepository.getLatLng(any())).thenReturn(
            Single.just(Response.success(response))
        )
        whenever(currentWeatherRepository.getCurrentWeather(any(), any())).thenReturn(
            Single.error(IOException())
        )

        getCurrentWeatherUseCase.invoke("") {}

        verify(currentWeatherRepository).getLatLng(any())
        verify(currentWeatherRepository).getCurrentWeather(any(), any())
        verifyNoMoreInteractions(currentWeatherRepository)
    }

}