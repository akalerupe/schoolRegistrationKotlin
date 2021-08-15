package com.example.registration.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.registration.Constants
import com.example.registration.R
import com.example.registration.ViewModel.CoursesViewModel
import com.example.registration.coursesAdapter
import com.example.registration.databinding.ActivityCodeHiveRegistrationBinding
import com.example.registration.models.Course
import com.example.registration.models.CoursesResponse

class codeHiveRegistration : AppCompatActivity() {
    lateinit var binding:ActivityCodeHiveRegistrationBinding
    val coursesViewModel:CoursesViewModel by viewModels()
    lateinit var sharedPrefs:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCodeHiveRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPrefs=getSharedPreferences(Constants.PREFS_FILE,Context.MODE_PRIVATE)
        displayCourses()
    }
    fun displayCourses(){
        var courseList= listOf(
            Course("iot","10t1","smart internet activities","Sir Barre"),
            Course("Android Development","kt601","Kotlin language","John Owuor"),
            Course("Front end web Development","web101","html/css/javascript languages","Purity Maina"),
            Course("Back end Development","py678","Python language","James Mwai")
             )
        binding.rvcourses
        var CoursesAdapter= coursesAdapter(courseList)
        binding.rvcourses.apply {
            layoutManager=LinearLayoutManager(baseContext)
            binding.rvcourses.adapter=CoursesAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        coursesViewModel.courseResponseLiveData.observe(this,{CoursesResponse ->
            var accessToken = sharedPrefs.getString(Constants.toString(),"ACCESS_TOKEN")
            var bearer = "Bearer $accessToken"
            if (accessToken!!.isNotEmpty()){
                coursesViewModel.displayCoursesList(accessToken)
            }
            else{
                startActivity(Intent(baseContext,Loginctivity::class.java))
            }
            var rvCoursesResponseAdapter=binding.rvcourses
            rvCoursesResponseAdapter.layoutManager = LinearLayoutManager(baseContext)

            var coursesResponseAdapter = CoursesResponseActivity(CoursesResponse)
            rvCoursesResponseAdapter.adapter = coursesResponseAdapter
            Toast.makeText(baseContext, "${CoursesResponse.size} courses fetched", Toast.LENGTH_LONG).show()
        })
        coursesViewModel.courseErrorLiveData.observe(this,{
                error->Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()

})
    }
    }
