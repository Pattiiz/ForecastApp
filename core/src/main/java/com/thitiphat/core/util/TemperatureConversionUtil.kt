package com.thitiphat.core.util

object TemperatureConversionUtil {

    fun fahrenheitToCelsius(fahrenheit: String): String {
        return (((fahrenheit.toFloat() - 32) * 5) / 9).toInt().toString()
    }

}