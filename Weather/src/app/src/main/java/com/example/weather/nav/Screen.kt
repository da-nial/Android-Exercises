package com.example.weather.nav


sealed class Screen(val route: String) {
    object ProvinceSelection : Screen("province_selection")
    object CitySelection : Screen("city_selection")
    object PlaceWeather : Screen("place_weather")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
