package com.example.mytestapp.data.model

import com.example.mytestapp.ui.interfaces.ISelected
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FilterModel(
        @SerializedName("id")
        @Expose
        val id: Int,
        @SerializedName("name")
        @Expose
        val name: String
) :ISelected

