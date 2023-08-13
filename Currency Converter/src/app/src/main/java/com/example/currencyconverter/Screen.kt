package com.example.currencyconverter

sealed class Screen(val route: String) {
    object SingleConvertScreen : Screen("single_convert_screen")
    object MultiConvertScreen : Screen("multi_convert_screen")
    object InitialCurrencyScreen : Screen("initial_currency_screen")
    object SecondaryCurrencyScreen : Screen("secondary_currency_screen")
}
