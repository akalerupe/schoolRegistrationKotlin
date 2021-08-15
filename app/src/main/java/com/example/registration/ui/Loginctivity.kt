package com.example.registration.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.viewModels
import com.example.registration.API.ApiClient
import com.example.registration.API.ApiInterface
import com.example.registration.R
import com.example.registration.ViewModel.UserViewModel
import com.example.registration.databinding.ActivityCodeHiveRegistrationBinding
import com.example.registration.databinding.ActivityLoginctivityBinding
import com.example.registration.databinding.ActivityMainBinding
import com.example.registration.models.LoginRequest
import com.example.registration.models.LoginResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class Loginctivity : AppCompatActivity() {
      lateinit var binding:ActivityLoginctivityBinding
      val userViewModel: UserViewModel by viewModels()
      lateinit var sharedPref:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginctivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPref=getSharedPreferences("CodeHiveRegistration",Context.MODE_PRIVATE)
//        setSupportActionBar(binding.logintoolbar)
        castViews()
        logInStudent()
    }
    fun castViews(){
       binding.tilusername.text.toString()
       binding.tilpassword.text.toString()
    }
    fun logInStudent(){
        var error=false
        var email= binding.tilusername.text.toString()
        var pswd=binding.tilpassword.text.toString()
            if (email.isEmpty()||email.isBlank()){
               binding.tilusername.setError("This field is compulsory")
                error=true
            }
            if (pswd.isEmpty()||pswd.isBlank()){
               binding.tilpassword.setError("This field is compulsory")
                error=true
        }

        binding.btnlogin.setOnClickListener {
            var intent=Intent(baseContext,codeHiveRegistration::class.java)
            startActivity(intent)
        }
//        val loginResponse=LoginRequest(
//            email=email,password = pswd
//        )
//        var retrofit=ApiClient.buildApiClient(ApiInterface::class.java)
//        var request=retrofit.logInStudent(loginRequest)
//        request.enqueue(object : Callback<LoginResponse> {
//            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
//                if (response.isSuccessful) {
//                    Toast.makeText(baseContext, "your login was succesful!", Toast.LENGTH_LONG)
//                        .show()
//                } else {
//                    try {
//                        val error = JSONObject(response.errorBody()!!.string())
//                        Toast.makeText(baseContext, error.toString(), Toast.LENGTH_LONG).show()
//                    }
//                    catch (e:Exception){
//                        Toast.makeText(baseContext, e.message, Toast.LENGTH_LONG).show()
//
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
//
//            }
//        })
//        userViewModel.logStudentIn(loginRequest)

    }
    override fun onResume() {

        super.onResume()
        userViewModel.logInResponseLiveData.observe(this,{ loginResponse ->
            var editor=sharedPref.edit()
            editor.putString("ACCESS_TOKEN",loginResponse.access_TOKEN)
            editor.putString("STUDENT_ID",loginResponse.studentId)
            editor.apply()
            Toast.makeText(baseContext,"Your login was succesful",Toast.LENGTH_LONG).show()
        })
        userViewModel.logInErrorLiveData.observe(this, { loginResponse->
            Toast.makeText(baseContext, "Your login was  not succesful", Toast.LENGTH_LONG).show()
        })
    }
}