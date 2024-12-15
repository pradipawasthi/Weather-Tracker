package com.pradip.weatherTracker.ui.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MainNavGraph() {
    val navController = androidx.navigation.compose.rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            // need to handle this
        }
        composable(Screen.Search.route) {
            // need to handle this

        }

        composable(Screen.Setting.route) {
            // need to handle this

        }
    }
}