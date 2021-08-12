package com.example.registration.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toolbar
import com.example.registration.API.ApiClient
import com.example.registration.API.ApiInterface
import com.example.registration.R
import com.example.registration.models.LoginRequest
import com.example.registration.models.LoginResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class Loginctivity : AppCompatActivity() {
      lateinit var logintoolbar:Toolbar
      lateinit var tilusername:EditText
      lateinit var tilpassword:EditText
      lateinit var btnlogin:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginctivity)
        setSupportActionBar(findViewById(R.id.logintoolbar))
        castViews()
        logInStudent()
    }
    fun castViews(){
        tilusername=findViewById(R.id.tilusername)
        tilpassword=findViewById(R.id.tilpassword)
        btnlogin=findViewById(R.id.btnlogin)
    }
    fun logInStudent(){
        var email=tilusername.text.toString()
        var pswd=tilpassword.text.toString()
        btnlogin.setOnClickListener {
            if (email.isEmpty()){
                tilusername.setError("This field is compulsory")
            }
            if (pswd.isEmpty()){
                tilpassword.setError("This field is compulsory")
            }
        }
        val loginRequest=LoginRequest(
            email=email,password = pswd
        )
        var retrofit=ApiClient.buildApiClient(ApiInterface::class.java)
        var request=retrofit.logInStudent(loginRequest)
        request.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(baseContext, "your login was succesful!", Toast.LENGTH_LONG)
                        .show()
                } else {
                    try {
                        val error = JSONObject(response.errorBody()!!.string())
                        Toast.makeText(baseContext, error.toString(), Toast.LENGTH_LONG).show()
                    }
                    catch (e:Exception){
                        Toast.makeText(baseContext, e.message, Toast.LENGTH_LONG).show()

                    }
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()

            }
        })

    }

}