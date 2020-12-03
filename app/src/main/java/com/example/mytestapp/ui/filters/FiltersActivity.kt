package com.example.mytestapp.ui.filters

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.example.mytestapp.R
import com.example.mytestapp.ui.adapters.DeliveryListAdapter
import com.example.mytestapp.ui.adapters.FiltersAdapter
import com.example.mytestapp.ui.adapters.FiltersCompanyAdapter
import com.example.mytestapp.ui.adapters.ViewPagerAdapter
import com.example.mytestapp.ui.interfaces.ISelected
import com.example.mytestapp.ui.interfaces.OnSelectedClickListener
import com.example.mytestapp.util.getListFromSharedPref
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_filters.*
import kotlinx.android.synthetic.main.activity_main.*

class FiltersActivity : AppCompatActivity() {

    private lateinit var viewModel: FiltersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filters)
        setSupportActionBar(toolbar_filters)

        viewModel = ViewModelProvider(this).get(FiltersViewModel::class.java)
        //viewModel.get()

        val viewPagerAdapter = ViewPagerAdapter(this)
        viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = if (position == 0)
                resources.getString(R.string.title_delivery)
            else
                resources.getString(R.string.title_filters)
        }.attach()

//        viewModel.countOfCompanies.observe(this){count ->
//            val items: String = resources.getQuantityString(R.plurals.plurals_2, count, count)
//            txt_count_selected_companies.text = items
//        }
    }
}