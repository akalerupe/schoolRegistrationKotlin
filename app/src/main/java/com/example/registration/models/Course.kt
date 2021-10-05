package com.example.registration.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Course")
data class Course(
    @PrimaryKey @SerializedName("course_id") val courseId:String,
    var coursename: String,
    var courseCode: String,
    var description: String,
    var instructor: String
)
