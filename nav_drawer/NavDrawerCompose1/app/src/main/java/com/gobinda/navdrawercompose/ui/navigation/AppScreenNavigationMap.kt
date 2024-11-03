package com.gobinda.navdrawercompose.ui.navigation

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gobinda.navdrawercompose.ui.drawer.AppDrawerContent
import com.gobinda.navdrawercompose.ui.screens.HomeScreen
import com.gobinda.navdrawercompose.ui.screens.ProfileScreen
import com.gobinda.navdrawercompose.ui.screens.SettingsScreen
import com.gobinda.navdrawercompose.ui.theme.NavDrawerComposeTheme
import kotlinx.coroutines.launch
import kotlin.Unit

@Composable
fun AppScreenNavigationMap() {
    NavDrawerComposeTheme {

        val navController = rememberNavController()
        val navActions = remember(navController) { AppScreenDestinationActions(navController) }
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val coroutineScope = rememberCoroutineScope()
        val drawerOpener: () -> Unit = {
            coroutineScope.launch { drawerState.open() }
        }

        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route ?: AppScreenDetails.HomeScreen.route

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                AppDrawerContent(
                    drawerState = drawerState,
                    currentRoute = currentRoute,
                    navigateToHome = navActions.navigateToHome,
                    navigateToSettings = navActions.navigateToSettings,
                    navigateToProfile = navActions.navigateToProfile,
                    closeDrawer = { coroutineScope.launch { drawerState.close() } }
                )
            }
        ) {
            NavHost(
                navController = navController,
                startDestination = AppScreenDetails.HomeScreen.route
            ) {
                composable(route = AppScreenDetails.HomeScreen.route) {
                    HomeScreen(drawerOpener)
                }
                composable(route = AppScreenDetails.ProfileScreen.route) {
                    ProfileScreen(drawerOpener)
                }
                composable(route = AppScreenDetails.SettingsScreen.route) {
                    SettingsScreen(drawerOpener)
                }
            }
        }
    }
}