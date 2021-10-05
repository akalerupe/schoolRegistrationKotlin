package com.example.registration.Repository

import androidx.lifecycle.LiveData
import com.example.registration.API.ApiClient
import com.example.registration.API.ApiInterface
import com.example.registration.CodeHiveDatabase
import com.example.registration.CodeHiveRegApplication
import com.example.registration.Database.CoursesDao
import com.example.registration.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class CoursesRepository @Inject constructor(var service:ApiInterface, val coursesDao: CoursesDao) {
//    val db=CodeHiveDatabase.getDatabase(CodeHiveRegApplication.appContext)
    suspend fun coursesList(accessToken:String){
      withContext(Dispatchers.IO){
          var response=service.fetchCourses(accessToken)
          response.body()?.forEach{ course ->
              coursesDao.insertCourse(course)
          }
      }
    fun getCoursesfromDb():LiveData<List<Course>>{
        return coursesDao.getAllCourses()
    }

    }


}