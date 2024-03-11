package com.example.mad_learninghubkt

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Navigation(val route: String) {
    object GetStartScreen: Navigation(route = "get_start_screen")
    object UserRegisterScreen: Navigation(route = "user_register_screen")
    object EmailVerificationScreen: Navigation(route = "email_verification_screen")
    object HomeScreen: Navigation(route = "Home_screen")
    object CourseDetails: Navigation(route = "course_details")
    object PaymentScreen: Navigation(route = "payment_screen")
    object ProfileScreen: Navigation(route = "profile_screen")
    object PersonalDetails: Navigation(route = "personal_details")
}