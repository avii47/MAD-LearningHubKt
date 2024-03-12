package com.example.mad_learninghubkt.data

import com.google.android.gms.maps.model.LatLng

data class BranchesItem(
    val branchName: String,
    val branchNo: String,
    val overview: String,
    val district: String,
    val address: String,
    val contactNo: String,
    val courses: String,
    val latLng: LatLng,
    val image: Int,
)