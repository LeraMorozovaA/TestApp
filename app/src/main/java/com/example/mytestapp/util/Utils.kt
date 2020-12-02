package com.example.mytestapp.util

import android.widget.ImageView
import android.widget.TextView
import com.example.mytestapp.R
import com.example.mytestapp.data.model.AvailableDeliveryModel

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