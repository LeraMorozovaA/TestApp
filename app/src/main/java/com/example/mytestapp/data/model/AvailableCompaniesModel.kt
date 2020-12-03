package com.example.mytestapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mytestapp.ui.interfaces.ISelected

@Entity(tableName = "available_companies_model")
data class AvailableCompaniesModel(
        @PrimaryKey
        val name: String,
        val id: String
) : ISelected