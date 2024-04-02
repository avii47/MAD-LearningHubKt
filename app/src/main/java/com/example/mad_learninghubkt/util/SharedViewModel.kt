package com.example.mad_learninghubkt.util

import android.content.Context
import android.util.Log
import android.widget.Toast
import android.widget.Toast.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.mad_learninghubkt.Navigation
import com.example.mad_learninghubkt.data.CoursesItem
import com.example.mad_learninghubkt.data.UserData
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SharedViewModel() : ViewModel() {

    fun saveUserData(
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

    fun checkCredentials(email: String, password: String, context: Context, navController: NavController) {
        viewModelScope.launch {
            try {
                val db = Firebase.firestore
                val document = db.collection("users").document(email).get().await()
                if (document.exists()) {
                    val userData = document.data
                    val storedPassword = userData?.get("password") as? String
                    if (storedPassword == password) {
                        handleSuccessfulLogin(document)
                        navController.navigate(route = Navigation.HomeScreen.route)

                    } else {
                        makeText(context, "Incorrect password", LENGTH_SHORT).show()
                    }
                } else {
                    makeText(context, "User not found", LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                makeText(context, "Error checking credentials: ${e.message}", LENGTH_SHORT).show()
            }
        }
    }

    private fun handleSuccessfulLogin(document: DocumentSnapshot) {

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
        UserDataStore.setUserData(userData)
    }

    fun fetchCourseData(context: Context) {
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("courses")

        viewModelScope.launch {
            try {
                val querySnapshot = collectionRef.get().await()

                for (document in querySnapshot.documents) {
                    val cid = (document.get("cid") as? Long)?.toInt() ?: 0
                    val title = document.get("title") as? String ?: ""
                    val overview = document.get("overview") as? String ?: ""
                    val level = document.get("level") as? String ?: ""
                    val duration = (document.get("duration") as? Long)?.toInt() ?: 0
                    val fee = (document.get("fee") as? Long)?.toInt() ?: 0
                    val max = (document.get("max") as? Long)?.toInt() ?: 0
                    val publishedDate = document.get("published date") as? String ?: ""
                    val closingDate = document.get("closing date") as? String ?: ""
                    val startingDate = document.get("starting date") as? String ?: ""
                    val branches = document.get("branches") as? String ?: ""
                    val image = (document.get("image") as? Long)?.toInt() ?: 0
                    val category = document.get("category") as? String ?: ""

                    val courseItem = CoursesItem(
                        cid = cid,
                        title = title,
                        overview = overview,
                        level = level,
                        duration = duration,
                        fee = fee,
                        max = max,
                        publishedDate = publishedDate,
                        closingDate = closingDate,
                        startingDate = startingDate,
                        branches = branches,
                        image = image,
                        category = category
                    )

                    // Add the created course item to the CourseDataStore
                    CourseDataStore.setCourseData(courseItem)
                }


            } catch (e: Exception) {
                // Handle exceptions
                Toast.makeText(context, "Error fetching course data", Toast.LENGTH_SHORT).show()
                throw e
            }
        }
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
