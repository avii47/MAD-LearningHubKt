package com.example.mad_learninghubkt

import PersonalDetailsScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetupNavGraph1(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Navigation.GetStartScreen.route
    ) {

        composable(
            route = Navigation.GetStartScreen.route
        ) {
            GetStartedScreen(navController = navController)
        }

        composable(
            route = Navigation.UserRegisterScreen.route
        ) {
            UserRegistraionScreen(navController = navController)
        }

        composable(
            route = Navigation.HomeScreen.route
        ) {
            MainScreen()
        }

//        composable(
//            route = Navigation.ProfileScreen.route
//        ) {
//            //ProfileScreen()
//        }


        composable(
            route = Navigation.PersonalDetails.route
        ) {
            PersonalDetailsScreen()
        }
    }
}

@Composable
fun SetupSubNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Navigation.ProfileScreen.route
    ) {

        composable(
            route = Navigation.ProfileScreen.route
        ) {
            ProfileScreen(navController)
        }

        composable(
            route = Navigation.PersonalDetails.route
        ) {
            PersonalDetailsScreen()
        }
    }
}