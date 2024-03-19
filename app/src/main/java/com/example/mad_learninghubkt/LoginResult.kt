package com.example.mad_learninghubkt

sealed class LoginResult {
    data class Success(val currentUserData: Map<String, Any>?) : LoginResult()
    data class Error(val errorMessage: String) : LoginResult()
}