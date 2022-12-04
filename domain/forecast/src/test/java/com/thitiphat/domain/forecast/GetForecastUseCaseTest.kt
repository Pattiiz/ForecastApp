package com.thitiphat.domain.forecast

import com.thitiphat.data.forecast.model.ForecastResponseModel
import com.thitiphat.data.forecast.repository.ForecastRepository
import io.reactivex.rxjava3.core.Single
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

class GetForecastUseCaseTest {

    @Mock
    private lateinit var forecastRepository: ForecastRepository

    private lateinit var getForecastUseCase: GetForecastUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getForecastUseCase = GetForecastUseCase(forecastRepository)
    }

    @Test
    fun getData_success() {
        val response = ForecastResponseModel(list = listOf())

        whenever(forecastRepository.getForecast(any(), any())).thenReturn(
            Single.just(
                Response.success(
                    response
                )
            )
        )

        getForecastUseCase.invoke("", "") {}

        verify(forecastRepository).getForecast(any(), any())
        verifyNoMoreInteractions(forecastRepository)
    }

    @Test
    fun getData_fail() {

        whenever(forecastRepository.getForecast(any(), any())).thenReturn(
            Single.error(IOException())
        )

        getForecastUseCase.invoke("", "") {}

        verify(forecastRepository).getForecast(any(), any())
        verifyNoMoreInteractions(forecastRepository)
    }
}