package com.example.registration.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.registration.R
import com.example.registration.coursesAdapter
import com.example.registration.models.Course

class codeHiveRegistration : AppCompatActivity() {
    lateinit var rvcourses:RecyclerView
    lateinit var btnnext:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code_hive_registration)
        displayCourses()
    }
    fun displayCourses(){
        var courseList= listOf(
            Course("iot","10t1","smart internet activities","Sir Barre"),
            Course("Android Development","kt601","Kotlin language","John Owuor"),
            Course("Front end web Development","web101","html/css/javascript languages","Purity Maina"),
            Course("Back end Development","py678","Python language","James Mwai")
            )
        rvcourses=findViewById(R.id.rvcourses)
        var CoursesAdapter= coursesAdapter(courseList)
        rvcourses.apply {
            layoutManager=LinearLayoutManager(baseContext)
            rvcourses.adapter=CoursesAdapter
        }
    }


}