package com.example.mad_learninghubkt.util

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import android.widget.Toast.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.mad_learninghubkt.Navigation
import com.example.mad_learninghubkt.data.BranchesItem
import com.example.mad_learninghubkt.data.CoursesItem
import com.example.mad_learninghubkt.data.UserData
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.ktx.firestore
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
                    val userRole = userData?.get("userName") as? String

                    if (storedPassword == password) {

                        if(userRole == "Admin"){
                            navController.navigate(route = Navigation.AdminDashboardScreen.route)
                        }
                        else{
                            handleSuccessfulLogin(document)
                            navController.navigate(route = Navigation.HomeScreen.route)
                        }

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
        val enrolledCoursesList = document.get("enrolled courses") as? List<String> ?: emptyList()
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
            enrolledCourses = enrolledCoursesList,
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

    fun fetchBranchData(context: Context) {
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("branches")
        val defaultGeoPoint = GeoPoint(0.0, 0.0)

        viewModelScope.launch {
            try {
                val querySnapshot = collectionRef.get().await()

                for (document in querySnapshot.documents) {
                    val branchNo = document.get("branch No") as? String ?: ""
                    val branchName = document.get("branch name") as? String ?: ""
                    val overview = document.get("overview") as? String ?: ""
                    val district = document.get("district") as? String ?: ""
                    val address = document.get("address") as? String ?: ""
                    val contactNo = document.get("contact No") as? String ?: ""
                    val courses = document.get("courses") as? String ?: ""
                    val latLng = document.getGeoPoint("map") as? GeoPoint ?: defaultGeoPoint
                    val image = (document.get("image") as? Long)?.toInt() ?: 0

                    val branchesItem = BranchesItem(
                        branchNo = branchNo,
                        branchName = branchName,
                        overview = overview,
                        district = district,
                        address = address,
                        contactNo = contactNo,
                        courses = courses,
                        latLng = latLng,
                        image = image
                    )

                    BranchDataStore.setBranchData(branchesItem)
                }


            } catch (e: Exception) {
                // Handle exceptions
                Toast.makeText(context, "Error fetching branch data", Toast.LENGTH_SHORT).show()
                throw e
            }
        }
    }

    fun fetchAllUsersData(context: Context) {
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("users")

        viewModelScope.launch {
            try {
                val querySnapshot = collectionRef.get().await()

                for (document in querySnapshot.documents) {
                    val userName = document.getString("userName") ?: ""
                    val address = document.getString("address") ?: ""
                    val livingCity = document.getString("livingCity") ?: ""
                    val dob = document.getString("dob") ?: ""
                    val nic = document.getString("nic") ?: ""
                    val email = document.getString("email") ?: ""
                    val gender = document.getString("gender") ?: ""
                    val mobileNo = document.getString("mobileNo") ?: ""
                    val enrolledCoursesList = document.get("enrolled courses") as? List<String> ?: emptyList()
                    val password = document.getString("password") ?: ""

                    val userItem = UserData(
                        userName = userName,
                        address = address,
                        livingCity = livingCity,
                        dob = dob,
                        nic = nic,
                        email = email,
                        gender = gender,
                        mobileNo = mobileNo,
                        enrolledCourses = enrolledCoursesList,
                        password = password
                    )

                    if(userName != "Admin"){
                        AllUsersDataStore.setAllUsersData(userItem)
                    }
                }

            } catch (e: Exception) {
                // Handle exceptions
                Toast.makeText(context, "Error fetching all users data", Toast.LENGTH_SHORT).show()
                throw e
            }
        }
    }

    fun enrollUserInCourse(userId: String, courseId: String, context: Context) {
        val db = Firebase.firestore
        val userRef = db.collection("users").document(userId)

        userRef.update("enrolled courses", FieldValue.arrayUnion(courseId))
            .addOnSuccessListener {
                Log.d(TAG, "User enrolled in course successfully.")
                Toast.makeText(context, "Successfully enrolled", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "Error enrolling user in course: ${e.message}", e)
            }
    }

    fun saveNewCourse(
        courseData: CoursesItem,
        context: Context,
        navController: NavController,
    ) = CoroutineScope(Dispatchers.IO).launch {

        val fireStoreRef = Firebase.firestore
            .collection("courses")
            .document(courseData.cid.toString())

        try {
            fireStoreRef.set(courseData)
                .addOnSuccessListener {
                    makeText(context, "Successfully added new course", LENGTH_SHORT).show()
                    Toast.makeText(context, "Successfully added new course", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                    fetchCourseData(context)
                }
        } catch (e: Exception) {
            makeText(context, e.message, LENGTH_SHORT).show()
        }
    }

    fun saveNewBranch(
        branchData: BranchesItem,
        context: Context,
        navController: NavController,
    ) = CoroutineScope(Dispatchers.IO).launch {

        val fireStoreRef = Firebase.firestore
            .collection("branches")
            .document(branchData.branchNo)

        try {
            fireStoreRef.set(branchData)
                .addOnSuccessListener {
                    makeText(context, "Successfully added new branch", LENGTH_SHORT).show()
                    Toast.makeText(context, "Successfully added new branch", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                    fetchBranchData(context)
                }
        } catch (e: Exception) {
            makeText(context, e.message, LENGTH_SHORT).show()
        }
    }

    fun CoursesItem.toMap(): Map<String, Any?> {
        return mapOf(
            "cid" to cid,
            "title" to title,
            "overview" to overview,
            "level" to level,
            "duration" to duration,
            "fee" to fee,
            "max" to max,
            "published date" to publishedDate,
            "closing date" to closingDate,
            "starting date" to startingDate,
            "branches" to branches,
            "category" to category,
            "image" to image
        )
    }

    fun updateCourse(courseData: CoursesItem, context: Context) = CoroutineScope(Dispatchers.IO).launch {
        val firestore = Firebase.firestore
        val courseId = courseData.cid.toString()

        try {
            firestore.collection("courses")
                .document(courseId)
                .update(courseData.toMap())
                .addOnSuccessListener {
                    makeText(context, "Successfully updated course", LENGTH_SHORT).show()
                    Toast.makeText(context, "Successfully updated course", Toast.LENGTH_SHORT).show()
                    fetchCourseData(context)
                }
        } catch (e: Exception) {
            makeText(context, "Error updating course: ${e.message}", LENGTH_SHORT).show()
        }
    }

    fun BranchesItem.toMap2(): Map<String, Any?> {
        return mapOf(
            "branch No" to branchNo,
            "branch name" to branchName,
            "overview" to overview,
            "district" to district,
            "address" to address,
            "contact No" to contactNo,
            "courses" to courses,
            "map" to latLng,
            "image" to image
        )
    }

    fun updateBranch(branchData: BranchesItem, context: Context) = CoroutineScope(Dispatchers.IO).launch {
        val firestore = Firebase.firestore
        val branchNo = branchData.branchNo

        Log.e("UpdateBranch", branchData.branchNo)

        try {
            firestore.collection("branches")
                .document(branchNo)
                .update(branchData.toMap2())
                .addOnSuccessListener {
                    CoroutineScope(Dispatchers.Main).launch {
                        Toast.makeText(context, "Successfully updated branch", Toast.LENGTH_SHORT).show()
                        fetchBranchData(context)
                    }
                }
                .addOnFailureListener { exception ->
                    CoroutineScope(Dispatchers.Main).launch {
                        Log.e("UpdateBranch", "Error updating branch: ${exception.message}", exception)
                        Toast.makeText(context, "Error updating branch: ${exception.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        } catch (e: Exception) {
            CoroutineScope(Dispatchers.Main).launch {
                Log.e("UpdateBranch", "Error updating branch: ${e.message}")
                Toast.makeText(context, "Error updating branch: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun deleteCourse(
        cid: String,
        context: Context,
        navController: NavController,
    ) = CoroutineScope(Dispatchers.IO).launch {

        val fireStoreRef = Firebase.firestore
            .collection("courses")
            .document(cid)

        try {
            fireStoreRef.delete()
                .addOnSuccessListener {
                    makeText(context, "Successfully deleted course", LENGTH_SHORT)
                        .show()
                    fetchCourseData(context)
                    navController.popBackStack()
                }
        } catch (e: Exception) {
            makeText(context, e.message, LENGTH_SHORT).show()
        }
    }

    fun deleteBranch(
        branchNo: String,
        context: Context,
        navController: NavController,
    ) = CoroutineScope(Dispatchers.IO).launch {

        val fireStoreRef = Firebase.firestore
            .collection("branches")
            .document(branchNo)

        try {
            fireStoreRef.delete()
                .addOnSuccessListener {
                    makeText(context, "Successfully deleted branch", LENGTH_SHORT)
                        .show()
                    fetchBranchData(context)
                    navController.popBackStack()
                }
        } catch (e: Exception) {
            makeText(context, e.message, LENGTH_SHORT).show()
        }
    }

    fun deleteUser(
        email: String,
        context: Context,
        navController: NavController,
    ) = CoroutineScope(Dispatchers.IO).launch {

        val fireStoreRef = Firebase.firestore
            .collection("users")
            .document(email)

        try {
            fireStoreRef.delete()
                .addOnSuccessListener {
                    makeText(context, "Successfully deleted user", LENGTH_SHORT)
                        .show()
                    navController.popBackStack()
                }
        } catch (e: Exception) {
            makeText(context, e.message, LENGTH_SHORT).show()
        }
    }
}





