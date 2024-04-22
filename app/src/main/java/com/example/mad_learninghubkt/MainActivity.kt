package com.example.mad_learninghubkt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mad_learninghubkt.ui.theme.MADLearningHubKtTheme
import com.example.mad_learninghubkt.util.SharedViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {

    private val sharedViewModel: SharedViewModel by viewModels()
    lateinit var navController: NavHostController
    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {

        //call the function that get the course list from the firebase
        sharedViewModel.fetchCourseData(context)
        sharedViewModel.fetchBranchData(context)
        sharedViewModel.fetchAllUsersData(context)

        super.onCreate(savedInstanceState)
        //PaymentConfiguration.init(applicationContext, "pk_test_51NfN7RJdkwpdiEIiViPOq1vCZMBeduWLq02jAELt0uH23HmfQvzJsbPX8mw9Q1LpJbVgsnkTb4nF9dQe1h1wFbbj000FIwBjM6")

        setContent {
            MADLearningHubKtTheme {

                navController = rememberNavController()
                SetupNavGraph1(
                    navController = navController,
                    sharedViewModel = sharedViewModel
                )
            }
        }
    }
}

@Composable
private fun SetBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(color = color)
    }
}