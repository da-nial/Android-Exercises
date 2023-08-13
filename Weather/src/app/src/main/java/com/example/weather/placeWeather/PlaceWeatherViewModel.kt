package com.example.weather.placeWeather

import android.util.Log
import androidx.lifecycle.*
import com.example.weather.network.WeatherAPI
import com.example.weather.network.WeatherData
import kotlinx.coroutines.launch

enum class WeatherApiStatus { LOADING, ERROR, DONE }

class PlaceWeatherViewModelFactory(private val lat: String, private val lon: String) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        PlaceWeatherViewModel(lat, lon) as T
}

class PlaceWeatherViewModel(private val lat: String, private val lon: String) : ViewModel() {
    private val _status = MutableLiveData<WeatherApiStatus>()
    val status: LiveData<WeatherApiStatus> = _status

    private val _weatherData = MutableLiveData<WeatherData>()
    val weatherData: LiveData<WeatherData> = _weatherData

    private val _lastUpdated = MutableLiveData<Long>()
    val lastUpdated: LiveData<Long> = _lastUpdated

    init {
        getWeatherData()

    }

    private fun getWeatherData() {
        viewModelScope.launch {
            _status.value = WeatherApiStatus.LOADING
            try {
                _weatherData.value = WeatherAPI.retrofitService.get5DaysData(lat, lon)
                Log.d("Response", _weatherData.toString())
                _status.value = WeatherApiStatus.DONE
                _lastUpdated.value = System.currentTimeMillis()
            } catch (e: Exception) {
                _status.value = WeatherApiStatus.ERROR
            }
        }
    }
}