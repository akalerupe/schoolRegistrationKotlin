package com.example.registration.DI

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.registration.API.ApiInterface
import com.example.registration.CodeHiveDatabase
import com.example.registration.CodeHiveRegApplication
import com.example.registration.Database.CoursesDao
import com.example.registration.Repository.CoursesRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
//container with bindings

@InstallIn(SingletonComponent::class)
object Appmodule {
    @Singleton
    @Provides

    fun provideRetrofit(client: OkHttpClient): Retrofit {
        var retrofit = Retrofit.Builder()
            .baseUrl("http://13.244.243.129")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit

    }
    @Provides
    fun provideOkHttpClient(){
        val client = OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor(CodeHiveRegApplication.appContext))
            .build()
    }
    @Provides
    fun provideApiInterface(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    fun provideDatabase(@ApplicationContext appContext: Context):
            CodeHiveDatabase {
        return CodeHiveDatabase.getDatabase(appContext)
    }
    @Singleton
    @Provides
    fun provideCoursesDao(database: CodeHiveDatabase): CoursesDao {
        return database.getAllCourses()
    }

    @Singleton
    @Provides
    fun provideCoursesRepository(coursesDao: CoursesDao, apiInterface:
    ApiInterface): CoursesRepository {
        return CoursesRepository(service =, apiInterface)
    }



}