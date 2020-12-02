package com.example.mytestapp.data.model

import com.example.mytestapp.ui.interfaces.ISelected
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AvailableDeliveryModel (
        @SerializedName("type")
        @Expose
        val type: Int,
        @SerializedName("title")
        @Expose
        val title: String
) : ISelected