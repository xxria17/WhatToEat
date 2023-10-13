package com.dhxxn17.whattoeat.ui.navigation

sealed class Screens (val route: String) {

    object HomeScreen: Screens("home_screen")

    object RouletteScreen: Screens("roulette_screen")
}