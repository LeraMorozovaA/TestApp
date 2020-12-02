package com.example.mytestapp.ui.filters

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.mytestapp.R
import com.example.mytestapp.ui.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_filters.*
import kotlinx.android.synthetic.main.activity_main.*

class FiltersActivity : AppCompatActivity() {

    private lateinit var viewModel: FiltersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filters)
        setSupportActionBar(toolbar_filters)

        val viewPagerAdapter = ViewPagerAdapter(this)
        viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = if (position == 0)
                resources.getString(R.string.title_delivery)
            else
                resources.getString(R.string.title_filters)
        }.attach()
    }
}