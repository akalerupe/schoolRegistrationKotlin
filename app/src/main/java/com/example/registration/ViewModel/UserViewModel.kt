package com.example.registration.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registration.Repository.UserRepository
import com.example.registration.models.LoginRequest
import com.example.registration.models.LoginResponse
import com.example.registration.models.RegistrationRequest
import com.example.registration.models.RegistrationResponse
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {

    var userRepository=UserRepository()
    var regResponseLiveData=MutableLiveData<RegistrationResponse>()
    var regErrorLiveData=MutableLiveData<String>()
    var logInResponseLiveData=MutableLiveData<LoginResponse>()
    var logInErrorLiveData=MutableLiveData<String>()

    fun registerStudent(registrationRequest: RegistrationRequest) {
        viewModelScope.launch {
            var response = userRepository.registerUser(registrationRequest)
            if (response.isSuccessful) {
                regResponseLiveData.postValue(response.body())
            } else {
                regErrorLiveData.postValue(response.errorBody()?.toString())
            }
        }
    }
    fun logStudentIn(loginRequest: LoginRequest){
viewModelScope.launch {
    var response=userRepository.logInStudent(loginRequest)
    if (response.isSuccessful){
        logInResponseLiveData.postValue(response.body())
    }
    else{
        logInErrorLiveData.postValue(response.errorBody()?.toString())
    }
}
    }
}