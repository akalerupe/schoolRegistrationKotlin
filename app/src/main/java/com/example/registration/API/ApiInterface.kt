package com.example.registration.API

import com.example.registration.models.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {
@POST("/students/register")

 suspend fun registerStudent(@Body registrationRequest: RegistrationRequest):Response<RegistrationResponse>

@POST("/students/register")
fun logInStudent(@Body loginRequest: LoginRequest):Call<LoginResponse>


 @GET("/courses")
 suspend fun fetchCourses(@Header("Authorization") token: String): Response<List<CoursesResponse>>

}