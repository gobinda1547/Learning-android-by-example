package com.gobinda.navdrawercompose.ui.navigation

sealed class AppScreenDetails(val route: String) {
    object HomeScreen : AppScreenDetails("home")
    object ProfileScreen : AppScreenDetails("profile")
    object SettingsScreen : AppScreenDetails("settings")
}