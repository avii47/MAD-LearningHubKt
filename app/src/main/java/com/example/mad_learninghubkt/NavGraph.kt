package com.example.mad_learninghubkt

import PersonalDetailsScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mad_learninghubkt.Admin.AddBranch
import com.example.mad_learninghubkt.Admin.AddCourse
import com.example.mad_learninghubkt.Admin.AdminBranchScreen
import com.example.mad_learninghubkt.Admin.AdminCourseScreen
import com.example.mad_learninghubkt.Admin.AdminDashboard
import com.example.mad_learninghubkt.Admin.AdminUsersScreen
import com.example.mad_learninghubkt.Admin.BranchOperations
import com.example.mad_learninghubkt.Admin.CourseOperations
import com.example.mad_learninghubkt.Admin.UserOperations
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
            MainScreen(sharedViewModel = sharedViewModel)
        }

        composable(
            route = Navigation.AdminDashboardScreen.route
        ) {
            AdminDashboard(navController = navController)
        }

        composable(
            route = Navigation.AdminCoursesScreen.route
        ) {
            AdminCourseScreen(navController = navController)
        }

        composable(
            route = Navigation.AdminUsersScreen.route
        ) {
            AdminUsersScreen(navController = navController)
        }

        composable(
            route = Navigation.AdminBranchScreen.route
        ) {
            AdminBranchScreen(navController = navController)
        }

        composable(
            route = Navigation.AddCourseScreen.route
        ) {
            AddCourse(navController = navController, sharedViewModel = sharedViewModel)
        }

        composable(
            route = Navigation.AddBranchScreen.route
        ) {
            AddBranch(navController = navController, sharedViewModel = sharedViewModel)
        }

        composable(
            route = "${Navigation.CourseOperationsScreen.route}/{courseId}",
            arguments = listOf(navArgument("courseId") { type = NavType.IntType })
        ) { backStackEntry ->
            val courseId = backStackEntry.arguments?.getInt("courseId") ?: 0
            CourseOperations(navController, sharedViewModel = sharedViewModel, courseId)
        }

        composable(
            route = "${Navigation.BranchOperationsScreen.route}/{branchId}",
            arguments = listOf(navArgument("branchId") { type = NavType.IntType })
        ) { backStackEntry ->
            val branchId = backStackEntry.arguments?.getInt("branchId") ?: 0
            BranchOperations(navController, sharedViewModel = sharedViewModel, branchId)
        }

        composable(
            route = "${Navigation.UserOperationsScreen.route}/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.IntType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("branchId") ?: 0
            UserOperations(navController, sharedViewModel = sharedViewModel, userId)
        }
    }
}

@Composable
fun SetupProfileNavGraph(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
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
            PersonalDetailsScreen(sharedViewModel)
        }
    }
}

@Composable
fun SetupHomeNavGraph(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
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
            PaymentScreen(navController, sharedViewModel)
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