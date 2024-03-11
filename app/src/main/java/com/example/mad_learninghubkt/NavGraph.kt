package com.example.mad_learninghubkt

import PersonalDetailsScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mad_learninghubkt.data.CoursesItem

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
fun SetupProfileNavGraph(
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

@Composable
fun SetupHomeNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Navigation.HomeScreen.route
    ) {

        composable(
            route = Navigation.HomeScreen.route
        ) {
            HomeScreen(navController)
        }

        composable(
            route = "${Navigation.CourseDetails.route}/{courseId}",
            arguments = listOf(navArgument("courseId") { type = NavType.IntType }) // Define argument for courseId
        ) { backStackEntry ->
            val courseId = backStackEntry.arguments?.getInt("courseId") ?: 0 // Default value if courseId is null
            CourseDetailsScreen(navController, courseId)
        }

        composable(
            route = Navigation.PaymentScreen.route
        ) {
            PaymentScreen(navController)
        }
    }
}