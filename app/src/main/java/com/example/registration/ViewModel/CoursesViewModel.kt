package com.example.registration.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registration.Repository.CoursesRepository
import com.example.registration.Repository.UserRepository
import com.example.registration.models.*
import kotlinx.coroutines.launch

class CoursesViewModel:ViewModel() {
    var coursesRepository = CoursesRepository()
    var courseResponseLiveData = MutableLiveData<List<CoursesResponse>>()
    var courseErrorLiveData = MutableLiveData<String>()

    fun displayCoursesList(accessToken: String) {
        viewModelScope.launch {
            var response = coursesRepository.coursesList(accessToken)
            if (response.isSuccessful) {
                courseResponseLiveData.postValue(response.body())
            } else {
                courseErrorLiveData.postValue(response.errorBody()?.string())
            }
        }

    }
}
