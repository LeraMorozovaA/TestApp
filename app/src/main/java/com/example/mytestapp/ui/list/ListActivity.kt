package com.example.mytestapp.ui.list

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytestapp.R
import com.example.mytestapp.data.State
import com.example.mytestapp.ui.adapters.ListAdapter
import com.example.mytestapp.ui.filters.FiltersActivity
import com.example.mytestapp.ui.filters.FiltersActivity.Companion.EXTRAS_STRING
import com.example.mytestapp.util.toCompaniesList
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list.*

class ListActivity : AppCompatActivity() {

    private lateinit var mAdapter: ListAdapter
    private lateinit var viewModel: ListViewModel
    private val REQUEST_CODE_FILTERS = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getText(R.string.title_home)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        val linearLManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLManager

        mAdapter = ListAdapter()
        recyclerView.adapter = mAdapter

        viewModel.getCompaniesList()
        viewModel.getFiltersModels()

        viewModel.companiesList.observe(this, Observer { list ->
            mAdapter.setData(list)
        })

        viewModel.state.observe(this, Observer { state ->
            when (state) {
                is State.LoggingState -> {
                    recyclerView.visibility = View.GONE
                    progress_bar.visibility = View.VISIBLE
                    filter_layout.isClickable = false
                }

                is State.SucceededState -> {
                    recyclerView.visibility = View.VISIBLE
                    progress_bar.visibility = View.GONE
                    filter_layout.isClickable = true
                }

                is State.ErrorState -> {
                    progress_bar.visibility = View.GONE
                    text_error.visibility = View.VISIBLE
                    filter_layout.isClickable = false
                }
            }
        })

        filter_layout.setOnClickListener {
            val intent = Intent(this, FiltersActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_FILTERS)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK)
            return

        if (requestCode == REQUEST_CODE_FILTERS){
            if (data != null){
                val list = data.getStringExtra(EXTRAS_STRING)?.toCompaniesList()
                viewModel.getFilteredList(list!!)
            } else
                return
        }
    }
}