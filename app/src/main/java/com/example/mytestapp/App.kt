package com.example.mytestapp

import android.app.Application
import com.example.mytestapp.api.CompaniesApi

class App: Application() {

    companion object {
        lateinit var instance: App
        lateinit var apiService: CompaniesApi.ApiInterface
//      lateinit var db: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
//        db = Room.databaseBuilder(this, AppDatabase::class.java , "database")
//            .build()
        apiService = CompaniesApi.getClient().create(
            CompaniesApi.ApiInterface::class.java)
    }
}