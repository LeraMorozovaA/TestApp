package com.example.mytestapp.ui.filters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytestapp.R
import com.example.mytestapp.data.model.AvailableCompaniesModel
import com.example.mytestapp.data.model.FilterModel
import com.example.mytestapp.ui.adapters.FiltersAdapter
import com.example.mytestapp.ui.adapters.FiltersCompanyAdapter
import com.example.mytestapp.ui.interfaces.ISelected
import com.example.mytestapp.ui.interfaces.OnCheckedClickListener
import com.example.mytestapp.ui.interfaces.OnSelectedClickListener
import kotlinx.android.synthetic.main.fragment_filters.*

class FiltersListFragment : Fragment(), OnSelectedClickListener, OnCheckedClickListener {

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
        mAdapter.setListeners(listener = this, unCheckedListener = this)
        companies_recycleView.adapter = mAdapter

        mFiltersAdapter = FiltersAdapter()
        mFiltersAdapter.setSelectedListener(this)
        filters_recycleView.adapter = mFiltersAdapter

        viewModel.getFiltersCompaniesList()
        viewModel.getFiltersList()

        viewModel.filtersCompaniesList.observe(viewLifecycleOwner, Observer { list ->
            mAdapter.setData(list)
        })

        viewModel.filtersList.observe(viewLifecycleOwner, Observer { list ->
            mFiltersAdapter.setData(list)
        })
    }

    override fun selectPosition(selectedItem: ISelected) {
        when (selectedItem) {
            is FilterModel -> viewModel.getFiltersClick(selectedItem.id)
            is AvailableCompaniesModel -> viewModel.getCompanyClick(selectedItem.id)
        }
    }

    override fun unChecked(selectedItem: ISelected) {
        val item: AvailableCompaniesModel = selectedItem as AvailableCompaniesModel
        viewModel.unCheckedPosition(item.id)
    }
}
