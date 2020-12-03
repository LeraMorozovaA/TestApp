package com.example.mytestapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mytestapp.data.model.*

@Database(entities = [FilterModel::class, DeliveryModel::class, CompanyModel::class, AvailableCompaniesModel::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun companiesModelDAO(): CompaniesDao
}