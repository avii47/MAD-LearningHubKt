package com.example.mad_learninghubkt.util

import com.example.mad_learninghubkt.data.UserData

object AllUsersDataStore {

    private var usersList: MutableList<UserData> = mutableListOf()

    fun setAllUsersData(user: UserData) {
        usersList.add(user)
    }

    fun getAllUsersData(): List<UserData> {
        return usersList.toList()
    }

    fun clearUserData() {
        usersList.clear()
    }
}