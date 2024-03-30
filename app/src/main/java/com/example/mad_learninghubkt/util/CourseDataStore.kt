package com.example.mad_learninghubkt.util

import com.example.mad_learninghubkt.data.CoursesItem

object CourseDataStore {
    private var courseList: MutableList<CoursesItem> = mutableListOf()

    fun setCourseData(course: CoursesItem) {
        courseList.add(course)
    }

    fun getCourseData(): List<CoursesItem> {
        return courseList.toList()
    }
}