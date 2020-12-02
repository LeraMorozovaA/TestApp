package com.example.mytestapp.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mytestapp.ui.filters.DeliveryListFragment
import com.example.mytestapp.ui.filters.FiltersListFragment
import java.util.ArrayList


class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragmentsList = ArrayList<Fragment>()

    init {
        fragmentsList.add(DeliveryListFragment())
        fragmentsList.add(FiltersListFragment())
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentsList[position]
    }

    override fun getItemCount(): Int {
        return fragmentsList.size
    }
}
