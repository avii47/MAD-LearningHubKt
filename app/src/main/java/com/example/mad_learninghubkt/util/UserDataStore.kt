package com.example.mad_learninghubkt.util

import com.example.mad_learninghubkt.data.UserData

object UserDataStore {
    private var userData: UserData? = null

    fun setUserData(user: UserData) {
        userData = user
    }

    fun getUserData(): UserData? {
        return userData
    }
}

