package com.example.mytestapp.data

import androidx.room.TypeConverter
import com.example.mytestapp.data.model.AvailableDeliveryModel
import com.example.mytestapp.data.model.CompanyModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converter {

    private val listOfObjects: Type = object : TypeToken<List<CompanyModel>>() {}.type

    @TypeConverter
    fun fromListCompanies(list: List<CompanyModel>): String {
        val gson = Gson()
        return gson.toJson(list, listOfObjects)
    }

    @TypeConverter
    fun toListCompanies(data: String ): List<CompanyModel> {
        val gson = Gson()
        return Gson().fromJson(data, listOfObjects)
    }

    @TypeConverter
    fun fromAvailableDeliveryModel(list: List<AvailableDeliveryModel>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun toAvailableDeliveryModel(data: String ): List<AvailableDeliveryModel> {
        val gson = Gson()
        return gson.fromJson(data, object: TypeToken< List<AvailableDeliveryModel> >() {}.type)
    }

    @TypeConverter
    fun fromActionInformerText(list: List<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun toActionInformerText(data: String ): List<String> {
        val gson = Gson()
        return gson.fromJson(data, object: TypeToken< List<String> >() {}.type)
    }

//    @TypeConverter
//    fun fromSpecializedCategoriesIds(list: List<Int>): String {
//        val gson = Gson()
//        return gson.toJson(list)
//    }
//
//    @TypeConverter
//    fun toSpecializedCategoriesIds(data: String ): List<Int> {
//        val gson = Gson()
//        return gson.fromJson(data, object: TypeToken< List<Int> >() {}.type)
//    }

    @TypeConverter
    fun fromListInt(list: List<Int>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun toListInt(data: String ): List<Int> {
        val gson = Gson()
        return gson.fromJson(data, object: TypeToken< List<Int> >() {}.type)
    }
}