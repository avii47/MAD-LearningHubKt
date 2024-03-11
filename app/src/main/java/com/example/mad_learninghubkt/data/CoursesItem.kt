package com.example.mad_learninghubkt.data

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CourseItem(course: CoursesItem, onClick: () -> Unit) {
    // Your CourseItem implementation
    Box(
        modifier = Modifier
            .clickable(onClick = onClick) // Invoke the provided onClick lambda
            .padding(8.dp)
    ) {
        // Example content displaying course details
        Text(text = course.title)
    }
}

data class CoursesItem(
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
    val image: Int
)