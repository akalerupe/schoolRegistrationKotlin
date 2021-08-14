package com.example.registration.Repository

import com.example.registration.API.ApiClient
import com.example.registration.API.ApiInterface
import com.example.registration.models.RegistrationRequest
import com.example.registration.models.RegistrationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
//    make the API call and return the response.
    var retrofit=ApiClient.buildApiClient(ApiInterface::class.java)
   suspend fun registerUser(registrationRequest: RegistrationRequest): Response<RegistrationResponse> =
        withContext(Dispatchers.IO){
            var response=retrofit.registerStudent(registrationRequest)
            return@withContext response
        }
}