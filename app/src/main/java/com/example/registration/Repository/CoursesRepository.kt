package com.example.registration.Repository

import androidx.lifecycle.LiveData
import com.example.registration.API.ApiClient
import com.example.registration.API.ApiInterface
import com.example.registration.CodeHiveDatabase
import com.example.registration.CodeHiveRegApplication
import com.example.registration.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CoursesRepository {
    var coursesInterface=ApiClient.buildApiClient(ApiInterface::class.java)
    val db=CodeHiveDatabase.getDatabase(CodeHiveRegApplication.appContext)
    suspend fun coursesList(accessToken:String){
      withContext(Dispatchers.IO){
          var response=coursesInterface.fetchCourses(accessToken)
          val dao=db.getAllCourses()
          response.body()?.forEach{ course ->
              dao.insertCourse(course)
          }
//          return@withContext response
      }
    fun getCoursesfromDb():LiveData<List<Course>>{
        return db.getAllCourses().getAllCourses()
    }

    }


}