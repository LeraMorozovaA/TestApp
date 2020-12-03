package com.example.mytestapp.ui.filters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytestapp.R
import com.example.mytestapp.ui.adapters.DeliveryListAdapter
import com.example.mytestapp.ui.interfaces.ISelected
import com.example.mytestapp.ui.interfaces.OnSelectedClickListener
import kotlinx.android.synthetic.main.activity_filters.*
import kotlinx.android.synthetic.main.fragment_delivery.*

class DeliveryListFragment: Fragment(), OnSelectedClickListener {

    private lateinit var mAdapter: DeliveryListAdapter
    private lateinit var viewModel: FiltersViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_delivery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(FiltersViewModel::class.java)

        val linearLManager = LinearLayoutManager(context)
        delivery_recyclerView.layoutManager = linearLManager

        val dividerItemDecoration =
                DividerItemDecoration(delivery_recyclerView.context, linearLManager.orientation)
        delivery_recyclerView.addItemDecoration(dividerItemDecoration)

        mAdapter = DeliveryListAdapter()
        mAdapter.setSelectedListener(this)
        delivery_recyclerView.adapter = mAdapter

        viewModel.getAvailableDeliveryList()

        viewModel.deliveryList.observe(viewLifecycleOwner) { list ->
            mAdapter.setData(list)
        }

        requireActivity().confirm_button.setOnClickListener {
            Toast.makeText(context, "!!!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun selectPosition(selectedItem: ISelected) {
        Toast.makeText(context, selectedItem.toString() , Toast.LENGTH_SHORT).show()

    }
}