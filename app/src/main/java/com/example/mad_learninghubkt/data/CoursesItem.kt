package com.example.mad_learninghubkt.data

data class CoursesItem(
    val cid: Int,
    val title: String,
    val overview: String,
    val level: String,
    val duration: Int,
    val fee: Int,
    val max: Int,
    val publishedDate: String,
    val closingDate: String,
    val startingDate: String,
    val branches: String,
    val image: Int,
    val category: String
)