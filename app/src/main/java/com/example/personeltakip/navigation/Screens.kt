package com.example.personeltakip.navigation

sealed class Screens(val route : String) {
    object SignInScreen : Screens(route = "SIGNIN_SCREEN")
    object SignUpScreen : Screens(route = "SIGNUP_SCREEN")
}
