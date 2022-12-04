package com.thitiphat.core.util

import java.text.SimpleDateFormat
import java.util.*

object TimeConversionUtil {

    fun unixTimeToGmt(dt: Int): String {
        val unixSeconds = dt.toLong()
        val date = Date(unixSeconds * 1000L)
        val sdf = SimpleDateFormat("dd-MM-yyyy @HH:mm")
        sdf.timeZone = TimeZone.getTimeZone("GMT+7")
        return sdf.format(date)
    }

}