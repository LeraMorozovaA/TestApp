package com.example.mytestapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mytestapp.ui.interfaces.ISelected
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity(tableName = "filter_model")
data class FilterModel(
        @PrimaryKey
        @SerializedName("id")
        @Expose
        val id: Int,
        @SerializedName("nameAccusative")
        @Expose
        val name: String
) :ISelected

