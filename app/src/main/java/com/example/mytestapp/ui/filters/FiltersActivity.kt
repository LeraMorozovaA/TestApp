package com.example.mytestapp.ui.filters

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mytestapp.R
import com.example.mytestapp.data.model.CompanyModel
import com.example.mytestapp.ui.adapters.ViewPagerAdapter
import com.example.mytestapp.util.toListString
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_filters.*
import java.lang.reflect.Type


class FiltersActivity : AppCompatActivity() {

    private lateinit var viewModel: FiltersViewModel
    private lateinit var json: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filters)
        setSupportActionBar(toolbar_filters)

        viewModel = ViewModelProvider(this).get(FiltersViewModel::class.java)
        viewModel.getCompaniesListSize()

        val viewPagerAdapter = ViewPagerAdapter(this)
        viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = if (position == 0)
                resources.getString(R.string.title_delivery)
            else
                resources.getString(R.string.title_filters)
        }.attach()

        viewModel.filteredList.observe(this, Observer { filteredList ->
            val count: Int = filteredList.size
            val items: String = resources.getQuantityString(R.plurals.plurals_2, count, count)
            txt_count_selected_companies.text = items
            json = filteredList.toListString()
        })

        confirm_button.setOnClickListener {
            val intent = Intent()
            intent.putExtra(EXTRAS_STRING, json)
            setResult(RESULT_OK, intent)
            finish()
        }

    }

    companion object{
       const val EXTRAS_STRING = "com.example.mytestapp.ui.filters.filtersActivity"
    }
}