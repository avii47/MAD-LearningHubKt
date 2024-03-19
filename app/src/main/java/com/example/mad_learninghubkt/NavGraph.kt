package com.example.mad_learninghubkt

import PersonalDetailsScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mad_learninghubkt.util.SharedViewModel


@Composable
fun SetupNavGraph1(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
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
            route = Navigation.UserLoginScreen.route
        ) {
            UserLoginScreen(navController = navController, sharedViewModel = sharedViewModel)
        }

        composable(
            route = Navigation.UserRegisterScreen.route
        ) {
            UserRegistraionScreen(navController = navController)
        }

        composable(
            route = Navigation.EmailVerificationScreen.route
        ) {
            EmailVerificationScreen(navController = navController, sharedViewModel = sharedViewModel)
        }

        composable(
            route = Navigation.HomeScreen.route
        ) {
            MainScreen()
        }

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
            arguments = listOf(navArgument("courseId") { type = NavType.IntType })
        ) { backStackEntry ->
            val courseId = backStackEntry.arguments?.getInt("courseId") ?: 0
            CourseDetailsScreen(navController, courseId)
        }

        composable(
            route = Navigation.PaymentScreen.route
        ) {
            PaymentScreen(navController)
        }
    }
}

@Composable
fun SetupBranchNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Navigation.BranchListScreen.route
    ) {

        composable(
            route = Navigation.BranchListScreen.route
        ) {
            BranchListScreen(navController)
        }

        composable(
            route = "${Navigation.BranchDetails.route}/{branchId}",
            arguments = listOf(navArgument("branchId") { type = NavType.IntType })
        ) { backStackEntry ->
            val branchId = backStackEntry.arguments?.getInt("branchId") ?: 0
            BranchDetailsScreen(navController, branchId)
        }

        composable(
            route = "${Navigation.LocationScreen.route}/{branchId}",
            arguments = listOf(navArgument("branchId") { type = NavType.IntType })
        ) { backStackEntry ->
            val branchId = backStackEntry.arguments?.getInt("branchId") ?: 0
            LocationsScreen(branchId)
        }
    }
}