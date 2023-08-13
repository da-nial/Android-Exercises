package com.example.weather

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weather.network.TEHRAN_WEATHER_DATA
import com.example.weather.network.WeatherData
import com.example.weather.network.WeatherDataItem
import com.example.weather.placeWeather.*
import com.example.weather.ui.theme.Typography
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun PlaceWeatherInfo(
    navController: NavController,
    placeWeatherViewModel: PlaceWeatherViewModel,
) {
    val apiStatus: WeatherApiStatus by placeWeatherViewModel.status.observeAsState(
        WeatherApiStatus.LOADING
    )
    val weatherData: WeatherData by placeWeatherViewModel.weatherData.observeAsState(
        TEHRAN_WEATHER_DATA
    )
    val lastUpdated: Long by placeWeatherViewModel.lastUpdated.observeAsState(0)

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter,
    ) {
        Image(
            painter = painterResource(id = getBackgroundImageForWeather(weatherData.items[0].hr[0].weather)),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (apiStatus == WeatherApiStatus.LOADING) {
                CircularProgressIndicator()
            } else if (apiStatus == WeatherApiStatus.ERROR) {
                Text(
                    text = "Could not fetch data. Please check your internet connection.",
                    style = Typography.h2
                )
            } else {
                WeatherOverview(weatherData)
                ForecastWeatherBox(weatherData, lastUpdated)
                WeatherDetails(weatherData)
            }
        }
    }
}

@Composable
fun WeatherOverview(weatherData: WeatherData) {
    Column(
        modifier = Modifier.padding(top = 50.dp, bottom = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = weatherData.city.name,
            style = Typography.h1,
            lineHeight = 0.sp
        )
        Text(
            text = "${weatherData.items[0].main.temp.toInt()}°",
            style = Typography.h2,
            fontSize = 60.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .padding(vertical = 0.dp),
        )
        Text(
            text = weatherData.items[0].hr[0].weather,
            style = Typography.h6,
            modifier = Modifier
                .padding(vertical = 0.dp)
        )
        Text(
            text = "H: ${weatherData.items[0].main.high_temp.toInt()}°  L: ${weatherData.items[0].main.low_temp.toInt()}°",
            style = Typography.h6,
            modifier = Modifier
                .padding(vertical = 0.dp)
        )
    }
}

@Composable
fun ForecastWeatherBox(weatherData: WeatherData, lastUpdated: Long) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .padding(bottom = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ForecastWeatherBoxHeader()
        Divider(color = Color.White.copy(alpha = 0.4f))
        ForecastWeatherBoxRow(weatherData.items[0], today = true)
        for (index in 8 until weatherData.items.lastIndex step 8) {
            ForecastWeatherBoxRow(weatherData.items[index])
        }
        ForcastWeatherBoxCaption(lastUpdated)
    }
}

@Composable
fun ForecastWeatherBoxHeader() {
    Row(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_calendar),
            "Calendar Icon",
            modifier = Modifier.size(24.dp),
            tint = Color.White
        )
        Text(text = "5-DAY FORECAST")
    }
}

@Composable
fun ForecastWeatherBoxRow(weatherDataItem: WeatherDataItem, today: Boolean = false) {
    val title = if (today) "Today" else getDayOfWeek(weatherDataItem.timestamp)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = Typography.body2,
            modifier = Modifier.fillMaxWidth(0.4f)
        )
        Icon(
            painter = painterResource(id = getIconForWeather(weatherDataItem.hr[0].icon)),
            contentDescription = "Today Icon",
            tint = Color.White
        )
        Text(text = weatherDataItem.main.high_temp.toInt().toString())
        Text(text = weatherDataItem.main.low_temp.toInt().toString())
    }
}

@Composable
fun ForcastWeatherBoxCaption(lastUpdated: Long) {
    val date = Date(lastUpdated)
    val displayDate = SimpleDateFormat("dd/MM/yy hh:mm a").format(date)

    Text(
        text = "Last updated at $displayDate",
        fontSize = 12.sp,
        color = Color.White.copy(alpha = 0.5f),
        modifier = Modifier.padding(top = 0.dp),
    )
}

@Composable
fun WeatherDetails(weatherData: WeatherData) {
    val sunrise = getHourOfDay(weatherData.city.sunrise).toString()
    val sunset = getHourOfDay(weatherData.city.sunset).toString()

    val humidity = weatherData.items[0].main.humidity.toString()
    val feelsLike = weatherData.items[0].main.feels_like.toInt().toString() + "°"
    val pressure = weatherData.items[0].main.pressure.toString()
    val seaLevel = weatherData.items[0].main.sea_level.toString()

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        WeatherDetailsRow(
            listOf("SUNRISE", "SUNSET", "FEELS LIKE"),
            listOf(sunrise, sunset, feelsLike)
        )
        WeatherDetailsRow(
            listOf("HUMIDITY", "SEA LEVEL", "PRESSURE"),
            listOf(humidity, seaLevel, pressure)
        )
    }
}

@Composable
fun WeatherDetailsRow(keys: List<String>, values: List<String>) {
    Divider(color = Color.White.copy(alpha = 0.4f))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (i in 0..2) {
            WeatherDetailsRowItem(key = keys[i], value = values[i])
        }
    }
}

@Composable
fun WeatherDetailsRowItem(key: String, value: String) {
    Column() {
        Text(text = key, color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
        Text(text = value)
    }
}