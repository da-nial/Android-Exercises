package com.example.weather.network

import com.squareup.moshi.Json

data class WeatherData(
    @Json(name = "city") val city: WeatherDataCity,
    @Json(name = "list") val items: List<WeatherDataItem>
)

data class WeatherDataItem(
    @Json(name = "dt") val timestamp: Long,
    @Json(name = "main") val main: WeatherDataItemMain,
    @Json(name = "weather") val hr: List<WeatherDataItemHr> // Human Readable Info
)

data class WeatherDataItemMain(
    @Json(name = "temp") val temp: Double = 0.0,
    @Json(name = "temp_min") val low_temp: Double = 0.0,
    @Json(name = "temp_max") val high_temp: Double = 0.0,
    @Json(name = "feels_like") val feels_like: Double = 0.0,
    @Json(name = "pressure") val pressure: Int = 0,
    @Json(name = "humidity") val humidity: Int = 0,
    @Json(name = "sea_level") val sea_level: Int = 0,
)

data class WeatherDataItemHr(
    @Json(name = "main") val weather: String,
    @Json(name = "icon") val icon: String
)

data class WeatherDataCity(
    @Json(name = "name") val name: String,
    @Json(name = "sunrise") val sunrise: Long = 0,
    @Json(name = "sunset") val sunset: Long = 0
)

val TEHRAN_WEATHER_DATA = WeatherData(
    WeatherDataCity("Tehran"),
    listOf(
        WeatherDataItem(
            0,
            WeatherDataItemMain(),
            listOf(WeatherDataItemHr("", ""))
        )
    )
)