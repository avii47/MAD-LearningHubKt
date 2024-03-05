package com.example.mad_learninghubkt

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen (
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home: BottomBarScreen("home", "Home", Icons.Default.Home)
    object Locations: BottomBarScreen("locations", "Locations", Icons.Default.LocationOn)
    object Account: BottomBarScreen("account", "Account", Icons.Default.AccountCircle)
}
