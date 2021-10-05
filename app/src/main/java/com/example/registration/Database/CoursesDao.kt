package com.example.registration.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.registration.models.Course

@Dao
interface CoursesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCourse(course:Course)

    @Query("SELECT * FROM Course")
    fun getAllCourses(): LiveData<List<Course>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCourses(vararg:Course )
}

