package com.example.mad_learninghubkt

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Navigation(val route: String) {
    object GetStartScreen: Navigation(route = "get_start_screen")
    object UserLoginScreen: Navigation(route = "user_login_screen")
    object UserRegisterScreen: Navigation(route = "user_register_screen")
    object EmailVerificationScreen: Navigation(route = "email_verification_screen")
    object HomeScreen: Navigation(route = "Home_screen")
    object CourseDetails: Navigation(route = "course_details")
    object PaymentScreen: Navigation(route = "payment_screen")
    object ProfileScreen: Navigation(route = "profile_screen")
    object PersonalDetails: Navigation(route = "personal_details")
    object BranchListScreen: Navigation(route = "branch_list_screen")
    object BranchDetails: Navigation(route = "branch_details")
    object LocationScreen: Navigation(route = "location_screen")
    object AdminDashboardScreen: Navigation(route = "admin_dashboard_screen")
    object AdminCoursesScreen: Navigation(route = "admin_courses_screen")
    object AdminUsersScreen: Navigation(route = "admin_users_screen")
    object AdminBranchScreen: Navigation(route = "admin_branch_screen")
    object CourseOperationsScreen: Navigation(route = "course_operations_screen")
    object BranchOperationsScreen: Navigation(route = "branch_operations_screen")
    object UserOperationsScreen: Navigation(route = "user_operations_screen")
    object AddCourseScreen: Navigation(route = "add_course_screen")
    object AddBranchScreen: Navigation(route = "add_branch_screen")
}