package com.example.registration.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.example.registration.API.ApiClient
import com.example.registration.API.ApiInterface
import com.example.registration.Constants
import com.example.registration.ViewModel.UserViewModel
import com.example.registration.databinding.ActivityMainBinding
import com.example.registration.models.RegistrationRequest
import com.example.registration.models.RegistrationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
lateinit var binding:ActivityMainBinding
lateinit var sharedPrefs:SharedPreferences
val userViewModel:UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPrefs=getSharedPreferences(Constants.PREFS_FILE,Context.MODE_PRIVATE)
        setUpSpinner()
        onClick()
        redirectUser()
    }
    fun redirectUser(){
        var token = sharedPrefs.getString(Constants.ACCESS_TOKEN, Constants.EMPTY_STRING)
        if (token!!.isNotEmpty()) {
            startActivity(Intent(baseContext, codeHiveRegistration::class.java))
        }
        else{
            startActivity(Intent(baseContext,Loginctivity::class.java))
        }
    }

    fun setUpSpinner() {
        val nationality = arrayOf("Kenyan", "Ugandan", "Rwandan", "Sudanese", "South Sudanese")
        val nationalityAdapter =
            ArrayAdapter(baseContext, android.R.layout.simple_spinner_item, nationality)
        nationalityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnationality.adapter = nationalityAdapter

    }

    fun onClick() {
       binding.btn.setOnClickListener {
            binding.progressBar3.visibility=View.VISIBLE
            var name = binding.tilname.text.toString()
            var dob =binding. etdob.text.toString()
            var nationality =binding. spnationality.selectedItem.toString().uppercase()
            var password =binding. etpswd.text.toString()
            var phone =binding. etphone.text.toString()
            var email =binding. etemail.text.toString()
            if(name.isEmpty()||dob.isEmpty()||password.isEmpty()||phone.isEmpty()||email.isEmpty()){
                binding.tilname.setError("please enter your name")
                binding.etdob.setError("Please enter your date of birth")
                binding.etpswd.setError("You must enter you ID number")
                binding.etphone.setError("please enter your phone number")
               binding. etemail.setError("please enter your email")
                Toast.makeText(baseContext,"This are your details :",Toast.LENGTH_LONG).show()
            }
            var intent=Intent(baseContext, Loginctivity::class.java)
            startActivity(intent)
            var regRequest=RegistrationRequest(
                name=name,phoneNumber = phone,email = email,dateOfBirth = dob,nationality = nationality,password = password
            )
           userViewModel.registerStudent(regRequest)

        }
        binding.btnnext.setOnClickListener {
           var intent=Intent(baseContext,Loginctivity::class.java)
           startActivity(intent)
       }
    }

    override fun onResume() {
        super.onResume()
        userViewModel.regResponseLiveData.observe(this,{ regResponse ->
            binding.progressBar3.visibility=View.GONE
            if(!regResponse.studentId.isNullOrEmpty()){
                Toast.makeText(baseContext,"registration succesful",Toast.LENGTH_LONG).show()
            }
        })
        userViewModel.regErrorLiveData.observe(this,{
            Toast.makeText(baseContext,"Your registration was not successful",Toast.LENGTH_LONG).show()

        })
    }
}