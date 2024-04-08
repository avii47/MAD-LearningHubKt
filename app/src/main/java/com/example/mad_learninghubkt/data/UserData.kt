package com.example.mad_learninghubkt.data

data class UserData(
    val userName: String,
    val address: String,
    val livingCity: String,
    val dob: String,
    val nic: String,
    val email: String,
    val gender: String,
    val mobileNo: String,
    val enrolledCourses: List<String>,
    val password: String,
    val image: Int
)

