package com.example.mad_learninghubkt.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class UserData (
    val userName: String,
    val address: String,
    val livingCity: String,
    val dob: String,
    val nic: String,
    val email: String,
    val gender: String,
    val mobileNo: String,
    val password: String
)

