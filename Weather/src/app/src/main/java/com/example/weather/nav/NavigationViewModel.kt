package com.example.weather.nav

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.placeWeather.PlaceWeatherViewModel


class NavigationViewModelViewModel : ViewModel() {
    private val _placeViewModel = MutableLiveData<List<PlaceWeatherViewModel>>()
    val placeViewModel: LiveData<List<PlaceWeatherViewModel>> = _placeViewModel
}