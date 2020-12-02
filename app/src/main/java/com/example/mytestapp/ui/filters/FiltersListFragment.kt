package com.example.mytestapp.ui.filters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytestapp.R
import com.example.mytestapp.ui.adapters.FiltersAdapter
import com.example.mytestapp.ui.adapters.FiltersCompanyAdapter
import com.example.mytestapp.ui.interfaces.ISelected
import com.example.mytestapp.ui.interfaces.OnSelectedClickListener
import kotlinx.android.synthetic.main.fragment_filters.*

class FiltersListFragment: Fragment(), OnSelectedClickListener {

    private lateinit var mAdapter: FiltersCompanyAdapter
    private lateinit var mFiltersAdapter: FiltersAdapter
    private lateinit var viewModel: FiltersViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_filters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(FiltersViewModel::class.java)

        val linearLManager1 = LinearLayoutManager(context)
        val linearLManager2 = LinearLayoutManager(context)
        companies_recycleView.layoutManager = linearLManager1
        filters_recycleView.layoutManager = linearLManager2

        val dividerItemDecoration1 =
                DividerItemDecoration(companies_recycleView.context, linearLManager1.orientation)
        companies_recycleView.addItemDecoration(dividerItemDecoration1)

        val dividerItemDecoration2 =
                DividerItemDecoration(filters_recycleView.context, linearLManager2.orientation)
        filters_recycleView.addItemDecoration(dividerItemDecoration2)

        mAdapter = FiltersCompanyAdapter()
        mAdapter.setSelectedListener(this)
        companies_recycleView.adapter = mAdapter

        mFiltersAdapter = FiltersAdapter()
        mFiltersAdapter.setSelectedListener(this)
        filters_recycleView.adapter = mFiltersAdapter

        viewModel.getFiltersCompaniesList()
        viewModel.getFiltersList()

        viewModel.filtersCompaniesList.observe(viewLifecycleOwner, { list ->
            mAdapter.setData(list)
        })

        viewModel.filtersList.observe(viewLifecycleOwner, { list ->
            mFiltersAdapter.setData(list)
        })
    }

    override fun selectPosition(selectedItem: ISelected) {
        Toast.makeText(context, selectedItem.toString() , Toast.LENGTH_SHORT).show()    }
}