package com.example.mad_learninghubkt.data

import com.google.firebase.firestore.GeoPoint

data class BranchesItem(
    var branchName: String,
    val branchNo: String,
    val overview: String,
    var district: String,
    var address: String,
    var contactNo: String,
    var courses: String,
    var latLng: GeoPoint,
    val image: Int,
)