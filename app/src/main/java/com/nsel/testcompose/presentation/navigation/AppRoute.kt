package com.nsel.testcompose.presentation.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings

sealed class AppRoute(val route: String) {
    object Login: AppRoute("login")
    object MainContent: AppRoute("mainContent")
}

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object Home: BottomNavItem(route = "Home", title = "Inicio", icon = Icons.Default.Home)
    object Profile: BottomNavItem(route = "Profile", title = "Perfil", icon = Icons.Default.Person)
    object Settings: BottomNavItem(route = "Settings", title = "Configuración", icon = Icons.Default.Settings)
}