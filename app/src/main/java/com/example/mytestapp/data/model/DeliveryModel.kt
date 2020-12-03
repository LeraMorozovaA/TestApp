package com.example.mytestapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.mytestapp.data.Converter
import com.example.mytestapp.ui.interfaces.ISelected
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@TypeConverters(Converter::class)
@Entity(tableName = "delivery_model")
data class DeliveryModel(
        @PrimaryKey
        @SerializedName("deliveryData")
        @Expose
        val list: List<AvailableDeliveryModel>,
) : ISelected