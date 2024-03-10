package com.example.mad_learninghubkt

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomBarScreen.Home.route) {
        composable(BottomBarScreen.Home.route) {
            HomeScreen()
        }
        composable(BottomBarScreen.Locations.route) {
            LocationsScreen()
        }
        composable(BottomBarScreen.Account.route) {

            lateinit var subNavController: NavHostController
            subNavController = rememberNavController()
            SetupSubNavGraph(navController = subNavController)
        }
    }
}