package com.example.mad_learninghubkt.data

data class CoursesItem(
    val cid: Int,
    var title: String,
    var overview: String,
    val level: String,
    var duration: Int,
    var fee: Int,
    var max: Int,
    var publishedDate: String,
    var closingDate: String,
    var startingDate: String,
    var branches: String,
    val image: Int,
    val category: String
)