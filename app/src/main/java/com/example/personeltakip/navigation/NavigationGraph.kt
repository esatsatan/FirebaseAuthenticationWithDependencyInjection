package com.example.personeltakip.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.personeltakip.view.SignInScreen
import com.example.personeltakip.view.SignUpScreen

@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screens.SignUpScreen.route
    ) {
       composable(route = Screens.SignInScreen.route) {
            SignInScreen(navController = navController)
       }
       composable(route = Screens.SignUpScreen.route) {
            SignUpScreen(navController = navController)
       }
    }
}