package com.example.registration.ui

import android.content.Intent
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
      lateinit var binding: ActivityLoginctivityBinding
      val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding = ActivityLoginctivityBinding.inflate(layoutInflater)
        setSupportActionBar(findViewById(R.id.logintoolbar))
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
        binding.btnlogin.setOnClickListener {
            if (email.isEmpty()){
               binding.tilusername.setError("This field is compulsory")
                error=true
            }
            if (pswd.isEmpty()){
               binding.tilpassword.setError("This field is compulsory")
                error=true
            }

        }
        val loginRequest=LoginRequest(
            email=email,password = pswd
        )
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
        userViewModel.logStudentIn(loginRequest)

    }
    override fun onResume() {
        super.onResume()
        userViewModel.logInResponseLiveData.observe(this,{ loginRequest ->
            Toast.makeText(baseContext,"Your login was succesful",Toast.LENGTH_LONG).show()
        })
        userViewModel.logInErrorLiveData.observe(this, { loginRequest ->
            Toast.makeText(baseContext, "Your login was succesful", Toast.LENGTH_LONG).show()
        })
    }


}