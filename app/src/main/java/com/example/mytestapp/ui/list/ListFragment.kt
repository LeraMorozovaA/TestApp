package com.example.mytestapp.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytestapp.R
import com.example.mytestapp.data.State
import com.example.mytestapp.ui.adapters.ListAdapter
import com.example.mytestapp.ui.filters.FiltersActivity
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private lateinit var mAdapter: ListAdapter
    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        val linearLManager = LinearLayoutManager(context)
        recyclerView.layoutManager = linearLManager

        mAdapter = ListAdapter()
        recyclerView.adapter = mAdapter

        viewModel.getCompaniesList()

        viewModel.companiesList.observe(viewLifecycleOwner, { list ->
            mAdapter.setData(list)
        })

        viewModel.state.observe(viewLifecycleOwner, { state ->
            when (state) {
                is State.LoggingState -> {
                    recyclerView.visibility = View.GONE
                    progress_bar.visibility = View.VISIBLE
                }

                is State.SucceededState -> {
                    recyclerView.visibility = View.VISIBLE
                    progress_bar.visibility = View.GONE
                }

                is State.ErrorState -> {
                    progress_bar.visibility = View.GONE
                    text_error.visibility = View.VISIBLE
                }
            }
        })

        filter_layout.setOnClickListener {
            val intent = Intent(context, FiltersActivity::class.java)
            startActivity(intent)
        }
    }
}