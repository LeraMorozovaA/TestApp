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

private val myPrefs: SharedPreferences =
    App.instance.applicationContext.getSharedPreferences("YOUR_PREFS_NAME", Context.MODE_PRIVATE)

fun Int.saveToSharedPref(key: String) {
    val prefsEditor: SharedPreferences.Editor = myPrefs.edit()
    prefsEditor.putInt(key, this)
    prefsEditor.apply()
}

fun getListFromSharedPref(key: String): Int {
    return myPrefs.getInt(key, -1)
}

private val ALL_DELIVERY = listOf(0, 1, 2, 3)
private val ALL_FILTERS = listOf(9, 8, 12, 5, 4, 7, 2)
private val newList = ArrayList<CompanyModel>()
private var count = 0

fun List<CompanyModel>.getResults(delivery: Int = 4, filters: Int = 0) { //1
    if (delivery == 4 && filters == 0) {
        newList.addAll(this)
        count = newList.size
        Log.i("UUUU", count.toString())

    } else if (delivery == 4 && filters != 0) {
        filterByFilters(filters, this)
    } else if (delivery != 4 && filters == 0) {
        filterByDelivery(delivery, this)
    } else {
        filterByDeliveryAndFilters(delivery, filters, this)
    }
}

fun filterByDelivery(delivery: Int, list: List<CompanyModel>) { // all filters one delivery
    for (i in list) {
        if (i.availableDeliveryTypes.contains(delivery)) {
            newList.add(i)
        } else
            continue
    }
    count = newList.size
    Log.i("UUUU", count.toString())
}

fun filterByFilters(filters: Int, list: List<CompanyModel>) { // all delivery one filters
    for (i in list) {
        if (i.specializedCategoriesIds.contains(filters)) {
            newList.add(i)
        } else
            continue
    }
    count = newList.size
    Log.i("UUUU", count.toString())
}

fun filterByDeliveryAndFilters(delivery: Int, filters: Int, list: List<CompanyModel>) { // one delivery and filters
    for (i in list) {
        if (i.specializedCategoriesIds.contains(filters)) {
            if (i.availableDeliveryTypes.contains(delivery))
                newList.add(i)
            else
                continue
        } else
            continue
    }
    count = newList.size
    Log.i("UUUU", count.toString())
}
