package com.example.mytestapp

import android.app.Application
import com.example.mytestapp.di.AppComponent
import com.example.mytestapp.di.DaggerAppComponent

class App: Application() {

    companion object {
        lateinit var instance: App
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder().build()
    }
}