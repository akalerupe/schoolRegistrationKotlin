package com.example.registration.models

data class CoursesResponse(
val date_created:String,
val date_updated:String,
val created_by:String,
val updated_by:String,
val active:Boolean,
val course_id:String,
val course_name:String,
val description:String,
val course_instructor:String,
)
