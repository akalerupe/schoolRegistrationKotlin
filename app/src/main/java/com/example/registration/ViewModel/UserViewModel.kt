package com.example.registration.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registration.Repository.UserRepository
import com.example.registration.models.RegistrationRequest
import com.example.registration.models.RegistrationResponse
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {

    var userRepository=UserRepository()
    var regResponseLiveData=MutableLiveData<RegistrationResponse>()
    var regErrorLiveData=MutableLiveData<String>()
    fun registerStudent(registrationRequest: RegistrationRequest) {
        viewModelScope.launch {
            var response = userRepository.registerUser(registrationRequest)
            if (response.isSuccessful) {
                regResponseLiveData.postValue(response.body())
            } else {
                regErrorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}