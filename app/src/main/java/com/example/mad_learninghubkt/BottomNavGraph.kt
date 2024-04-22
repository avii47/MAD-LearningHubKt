package com.example.mad_learninghubkt

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mad_learninghubkt.util.SharedViewModel

@Composable
fun BottomNavGraph(navController: NavHostController, sharedViewModel: SharedViewModel) {
    NavHost(navController = navController, startDestination = BottomBarScreen.Home.route) {
        composable(BottomBarScreen.Home.route) {
            lateinit var homeNavController: NavHostController
            homeNavController = rememberNavController()
            SetupHomeNavGraph(navController = homeNavController, sharedViewModel)
        }
        composable(BottomBarScreen.Locations.route) {
            lateinit var branchNavController: NavHostController
            branchNavController = rememberNavController()
            SetupBranchNavGraph(navController = branchNavController)
        }
        composable(BottomBarScreen.Account.route) {
            lateinit var profileNavController: NavHostController
            profileNavController = rememberNavController()
            SetupProfileNavGraph(navController = profileNavController, sharedViewModel)
        }
    }
}