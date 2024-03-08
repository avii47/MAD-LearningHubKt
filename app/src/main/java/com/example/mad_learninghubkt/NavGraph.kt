package com.example.mad_learninghubkt

import PersonalDetailsScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Navigation.ProfileScreen.route
    ) {

        composable(
            route = Navigation.ProfileScreen.route
        ) {
            ProfileScreen(navController = navController)
        }

        composable(
            route = Navigation.PersonalDetails.route
        ) {
            PersonalDetailsScreen()
        }
    }
}