package com.example.mytestapp.di

import android.content.Context
import androidx.room.Room
import com.example.mytestapp.App
import com.example.mytestapp.data.AppDatabase
import com.example.mytestapp.data.CompaniesDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideContext(): Context = App.instance.applicationContext

    @Provides
    @Singleton
    fun provideRoomDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "database")
            .build()


    @Provides
    @Singleton
    fun provideDatabaseDao(database: AppDatabase): CompaniesDao =
        database.companiesModelDAO()

}