package com.example.mytestapp.data.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.mytestapp.data.Converter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@TypeConverters(Converter::class)
@Entity(tableName = "company_model")
data class CompanyModel (

        @PrimaryKey
        @NonNull
        @SerializedName("id")
        @Expose
        var id: Int,

        @SerializedName("name")
        @Expose
        var name: String,

        @SerializedName("status")
        @Expose
        var status: Int,

        @SerializedName("image")
        @Expose
        var image: String,

        @SerializedName("todayWorkingTimeText")
        @Expose
        var todayWorkingTimeText: String,

        @SerializedName("isWorkingNow")
        @Expose
        var isWorkingNow: Int,

        @SerializedName("actionText")
        @Expose
        var actionText: String,

        @SerializedName("actionInformerText")
        @Expose
        var actionInformerText: List<String>,

        @SerializedName("specializedCategoriesIds")
        @Expose
        var specializedCategoriesIds: List<Int>,

        @SerializedName("availableDeliveryTypes")
        @Expose
        var availableDeliveryTypes: List<Int>,

        @SerializedName("terminalPayment")
        @Expose
        var terminalPayment: Int,

        @SerializedName("onlinePayment")
        @Expose
        var onlinePayment: Int,

        @SerializedName("rating")
        @Expose
        var rating: Double? = null,

        @SerializedName("reviewsCount")
        @Expose
        var reviewsCount: Int,

        @SerializedName("deliveryPriceText")
        @Expose
        var deliveryPriceText: String

)