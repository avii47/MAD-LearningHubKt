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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MADLearningHubKtTheme {

//                FirebaseAnalytics.getInstance(this).setAnalyticsCollectionEnabled(true);
//                FirebaseAnalytics.getInstance(this).setUserProperty("debug", "true");

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