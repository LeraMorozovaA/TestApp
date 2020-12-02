package com.example.mytestapp.data.model

import androidx.annotation.NonNull
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

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
        var specializedCategoriesIds: List<Int>? = null,

        @SerializedName("recommendedCategoriesIds")
        @Expose
        var recommendedCategoriesIds: List<Any>? = null,

        @SerializedName("availableDeliveryTypes")
        @Expose
        var availableDeliveryTypes: List<Int>? = null, //какие способы доставки

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