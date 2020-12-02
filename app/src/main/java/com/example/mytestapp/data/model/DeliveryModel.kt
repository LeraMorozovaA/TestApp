package com.example.mytestapp.data.model

import com.example.mytestapp.ui.interfaces.ISelected
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DeliveryModel(
        @SerializedName("deliveryData")
        @Expose
        val list: List<AvailableDeliveryModel>,
) : ISelected