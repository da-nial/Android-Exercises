package com.example.weather.placeWeather

import com.example.weather.R
import java.text.SimpleDateFormat
import java.util.*

fun getBackgroundImageForWeather(weather: String): Int {
    return when (weather) {
        "Rain" -> R.drawable.showers
        "Cloud" -> R.drawable.mostly_cloudy
        "Clouds" -> R.drawable.mostly_cloudy_2
        "Snow" -> R.drawable.flurries
        "Unknown2" -> R.drawable.fog
        "Unknown3" -> R.drawable.haze
        "Unknown4" -> R.drawable.drizzle
        "Unknown5" -> R.drawable.mostly_clear
        else -> R.drawable.mostly_clear
    }
}

fun getIconForWeather(icon: String): Int {
    return when (icon) {
        "01d" -> R.drawable.ic_01d
        "02d" -> R.drawable.ic_02d
        "03d" -> R.drawable.ic_03d
        "04d" -> R.drawable.ic_04d
        "09d" -> R.drawable.ic_09d
        "10d" -> R.drawable.ic_10d
        "11d" -> R.drawable.ic_11d
        "13d" -> R.drawable.ic_13d
        "50d" -> R.drawable.ic_50d
        "01n" -> R.drawable.ic_01n
        "02n" -> R.drawable.ic_02n
        "03n" -> R.drawable.ic_03n
        "04n" -> R.drawable.ic_04n
        "09n" -> R.drawable.ic_09n
        "10n" -> R.drawable.ic_10n
        "11n" -> R.drawable.ic_11n
        "13n" -> R.drawable.ic_13n
        "50n" -> R.drawable.ic_50n
        else -> R.drawable.ic_01d
    }
}

fun getDayOfWeek(timestamp: Long): String {
    return SimpleDateFormat("EEEE", Locale.ENGLISH).format(timestamp * 1000)
}

fun getHourOfDay(timestamp: Long): Int {
    val date = Date(timestamp)
    val cal = Calendar.getInstance()
    cal.time = date
    val hours = cal.get(Calendar.HOUR_OF_DAY)
    return hours
}
