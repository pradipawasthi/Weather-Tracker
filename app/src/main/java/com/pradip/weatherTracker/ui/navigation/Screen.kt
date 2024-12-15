package com.pradip.weatherTracker.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Search : Screen("search")
    object Setting : Screen("setting")


}