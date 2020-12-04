package com.example.mytestapp.di

import com.example.mytestapp.data.repository.DataRepository
import dagger.Component
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class, RoomModule::class])
interface AppComponent {

    fun inject(dataRepository: DataRepository)

}