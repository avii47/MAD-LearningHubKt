package com.example.mad_learninghubkt.util

import android.content.Context
import android.widget.Toast
import android.widget.Toast.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.mad_learninghubkt.LoginResult
import com.example.mad_learninghubkt.Navigation
import com.example.mad_learninghubkt.data.UserData
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SharedViewModel() : ViewModel() {

    fun saveData(
        userData: UserData,
        context: Context
    ) = CoroutineScope(Dispatchers.IO).launch {

        val fireStoreRef = Firebase.firestore
            .collection("users")
            .document(userData.email)

        try {
            fireStoreRef.set(userData)
                .addOnSuccessListener {
                    makeText(context, "Successfully saved data", LENGTH_SHORT).show()
                }
        } catch (e: Exception) {
            makeText(context, e.message, LENGTH_SHORT).show()
        }
    }

    private val _loginResult = MutableStateFlow<LoginResult?>(null)
    val loginResult: StateFlow<LoginResult?> = _loginResult

    fun checkCredentials(email: String, password: String, context: Context, navController: NavController) {
        viewModelScope.launch {
            try {
                val db = Firebase.firestore
                val document = db.collection("users").document(email).get().await()
                if (document.exists()) {
                    val userData = document.data
                    val storedPassword = userData?.get("password") as? String
                    if (storedPassword == password) {
                        handleSuccessfulLogin(document, context)
                        _loginResult.value = LoginResult.Success(userData)
                        navController.navigate(route = Navigation.HomeScreen.route)

                    } else {
                        _loginResult.value = LoginResult.Error("Incorrect password")
                    }
                } else {
                    _loginResult.value = LoginResult.Error("User not found")
                }
            } catch (e: Exception) {
                _loginResult.value = LoginResult.Error("Error checking credentials: ${e.message}")
            }
        }
    }

    private fun handleSuccessfulLogin(document: DocumentSnapshot, context: Context) {

        // Retrieve user data from Firestore document
        val userName = document.getString("userName") ?: ""
        val address = document.getString("address") ?: ""
        val livingCity = document.getString("livingCity") ?: ""
        val dob = document.getString("dob") ?: ""
        val nic = document.getString("nic") ?: ""
        val email = document.getString("email") ?: ""
        val gender = document.getString("gender") ?: ""
        val mobileNo = document.getString("mobileNo") ?: ""
        val password = document.getString("password") ?: ""

        // Create an instance of UserData using the retrieved data
        val userData = UserData(
            userName = userName,
            address = address,
            livingCity = livingCity,
            dob = dob,
            nic = nic,
            email = email,
            gender = gender,
            mobileNo = mobileNo,
            password = password
        )

        //makeText(context, userName, LENGTH_SHORT).show()

        UserDataStore.setUserData(userData)
    }
}

    fun retrieveData(
        nic: String,
        context: Context,
        data: (UserData) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {

        val fireStoreRef = Firebase.firestore
            .collection("users")
            .document(nic)

        try {
            fireStoreRef.get()
                .addOnSuccessListener {
                    // for getting single or particular document
                    if (it.exists()) {
                        val userData = it.toObject<UserData>()!!
                        data(userData)
                    } else {
                        makeText(context, "No User Data Found", LENGTH_SHORT).show()
                    }
                }
        } catch (e: Exception) {
            makeText(context, e.message, LENGTH_SHORT).show()
        }
    }

    fun deleteData(
        userID: String,
        context: Context,
        navController: NavController,
    ) = CoroutineScope(Dispatchers.IO).launch {

        val fireStoreRef = Firebase.firestore
            .collection("user")
            .document(userID)

        try {
            fireStoreRef.delete()
                .addOnSuccessListener {
                    makeText(context, "Successfully deleted data", LENGTH_SHORT)
                        .show()
                    navController.popBackStack()
                }
        } catch (e: Exception) {
            makeText(context, e.message, LENGTH_SHORT).show()
        }
    }
