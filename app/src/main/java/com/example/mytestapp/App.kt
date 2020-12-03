package com.example.mytestapp

import android.app.Application
import androidx.room.Room
import com.example.mytestapp.api.CompaniesApi
import com.example.mytestapp.data.AppDatabase

class App: Application() {

    companion object {
        lateinit var instance: App
        lateinit var apiService: CompaniesApi.ApiInterface
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(
            this, AppDatabase::class.java, "database")
            .build()

        apiService = CompaniesApi.getClient().create(
            CompaniesApi.ApiInterface::class.java)
    }
}