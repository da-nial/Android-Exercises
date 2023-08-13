package com.example.falehafez

sealed class Screen(val route: String) {
    object MainScreen: Screen("main_screen")
    object PeomScreen: Screen("poem_screen")
    object AboutScreen: Screen("about_screen")
}
