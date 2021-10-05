package com.example.registration

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.registration.Database.CoursesDao
import com.example.registration.models.Course

@Database(entities = arrayOf(Course::class),version = 1)
 abstract class CodeHiveDatabase:RoomDatabase() {
    abstract fun getAllCourses(): CoursesDao
    companion object {
        private var database: CodeHiveDatabase? = null
        fun getDatabase(context: Context): CodeHiveDatabase {
            if (database == null) {
                database = Room.databaseBuilder(context,
                    CodeHiveDatabase::class.java, "codeHiveDb")
                    .fallbackToDestructiveMigration().build()
            }
            return database as CodeHiveDatabase
        }
    }
}

