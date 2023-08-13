package com.example.weather

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.weather.nav.Screen
import com.example.weather.placeSelection.CitySelection
import com.example.weather.placeWeather.PlaceWeatherViewModelFactory
import com.google.android.gms.location.FusedLocationProviderClient

@Composable
fun Navigation(
    fusedLocationProviderClient: FusedLocationProviderClient,
    context: Context,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.ProvinceSelection.route
    ) {
        composable(route = Screen.ProvinceSelection.route) {
            ProvinceSelection(
                fusedLocationProviderClient,
                context = context,
                navController = navController,
            )
        }
        composable(
            route = Screen.CitySelection.route + "/{province}",
            arguments = listOf(
                navArgument("province") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            val requestedProvince =
                PROVINCES.find { province ->
                    province.title == entry.arguments?.getString("province")
                }
            CitySelection(
                requestedProvince!!,
                navController = navController,
            )
        }
        composable(
            route = Screen.PlaceWeather.route + "/{lat}/{lon}",
            arguments = listOf(
                navArgument("lat") {
                    type = NavType.StringType
                },
                navArgument("lon") {
                    type = NavType.StringType
                },
            )
        ) { entry ->
            PlaceWeatherInfo(
                navController = navController,
                viewModel(
                    factory = PlaceWeatherViewModelFactory(
                        lat = entry.arguments?.getString("lat").toString(),
                        lon = entry.arguments?.getString("lon").toString(),
                    ),
                )
            )
        }
    }
}