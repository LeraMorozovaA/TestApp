package com.example.mytestapp.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.mytestapp.App
import com.example.mytestapp.R
import com.example.mytestapp.data.model.AvailableDeliveryModel
import com.example.mytestapp.data.model.CompanyModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.math.BigInteger
import java.util.ArrayList


fun TextView.setTitle(model: AvailableDeliveryModel) {
    if (model.type == 2)
        this.text = resources.getText(R.string.text_type_2)
    else
        this.text = model.title
}

fun ImageView.setIcon(type: Int) {
    when (type) {
        0 -> this.setImageResource(R.drawable.ic_home)
        1 -> this.setImageResource(R.drawable.ic_door)
        2 -> this.setImageResource(R.drawable.ic_pickup)
        3 -> this.setImageResource(R.drawable.ic_booking)
        4 -> this.setImageResource(R.drawable.ic_bag)
    }
}

private val type: Type = object : TypeToken<List<CompanyModel>>() {}.type

fun List<CompanyModel>.toListString(): String{
   return Gson().toJson(this, type)
}

fun String.toCompaniesList(): List<CompanyModel>{
    return Gson().fromJson(this, type)
}

fun List<CompanyModel>.getResults(delivery: Int, filters: Int, company: String, unChecked: Boolean): List<CompanyModel> {
    val list = ArrayList<CompanyModel>()
    list.addAll(elements = this)

    if (delivery != 4)
      list.retainAll(filterByDelivery(delivery, this))

    if (filters != 0)
        list.retainAll(filterByFilters(filters, this))

    if (!unChecked) {
        if (company.isNotEmpty())
            list.retainAll(filterByCompany(company, this))
    }

    return list
}


fun filterByDelivery(delivery: Int, list: List<CompanyModel>): ArrayList<CompanyModel> {
    val newList = ArrayList<CompanyModel>()
    for (i: CompanyModel in list) {
        if (i.availableDeliveryTypes.contains(delivery)) {
            newList.add(i)
        } else
            continue
    }

    return newList
}

fun filterByFilters(filters: Int, list: List<CompanyModel>): ArrayList<CompanyModel> {
    val newList = ArrayList<CompanyModel>()
    for (i: CompanyModel in list) {
        if (i.specializedCategoriesIds.contains(filters)) {
            newList.add(i)
        } else
            continue
    }

    return newList
}

fun filterByCompany(company: String, list: List<CompanyModel>): ArrayList<CompanyModel> {
    val newList = ArrayList<CompanyModel>()
    if (company == "status"){
        for (i: CompanyModel in list) {
            if (i.status == 1 && i.isWorkingNow == 1) {
                newList.add(i)
            } else
                continue
        }
    } else if (company == "onlinePayment"){
        for (i: CompanyModel in list) {
            if (i.onlinePayment == 1) {
                newList.add(i)
            } else
                continue
        }
    }

    return newList
}
