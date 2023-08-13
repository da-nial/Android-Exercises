package com.example.falehafez

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.currencyconverter.*

@Composable
fun Navigation() {
    val navController = rememberNavController()
    var initialCurrencyName: String by rememberSaveable { mutableStateOf("USD") }
    var secondaryCurrencyName: String by rememberSaveable { mutableStateOf("GBP") }

    NavHost(
        navController = navController,
        startDestination = Screen.SingleConvertScreen.route
    ) {
        composable(route = Screen.MultiConvertScreen.route) {
            MultiConvertScreen()
        }
        composable(route = Screen.SingleConvertScreen.route) {
            SingleConvertScreen(
                initialCurrencyName = initialCurrencyName,
                secondaryCurrencyName = secondaryCurrencyName,
                navController = navController
            )
        }
        composable(route = Screen.InitialCurrencyScreen.route) {
            CurrencyScreen(
                title = "You Pay",
                onClick = {
                    initialCurrencyName = it;
                    navController.navigate(Screen.SecondaryCurrencyScreen.route)
                },
            )
        }
        composable(route = Screen.SecondaryCurrencyScreen.route) {
            CurrencyScreen(
                title = "You Get",
                onClick = {
                    secondaryCurrencyName = it;
                    navController.navigate(Screen.SingleConvertScreen.route)
                }
            )
        }
    }
}