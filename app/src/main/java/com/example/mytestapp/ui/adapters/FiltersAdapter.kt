package com.example.mytestapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.R
import com.example.mytestapp.data.model.*
import com.example.mytestapp.ui.interfaces.OnSelectedClickListener
import kotlinx.android.synthetic.main.item_line_filters_radiobutton.view.*

class FiltersAdapter : RecyclerView.Adapter<FiltersAdapter.FiltersListViewHolder>() {

    private var mDataList: List<FilterModel> = ArrayList()
    lateinit var selectedClickListener: OnSelectedClickListener
    var mSelected = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FiltersListViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View =
            layoutInflater.inflate(R.layout.item_line_filters_radiobutton, parent, false)
        return  FiltersListViewHolder(view)
    }

    override fun onBindViewHolder(holder: FiltersListViewHolder, position: Int) {
            holder.bind(mDataList[position] )
            holder.mRadioButton.setChecked(mSelected == position)
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    fun setData(dataList: List<FilterModel>) {
        mDataList = dataList
        notifyDataSetChanged()
    }

    fun setSelectedListener(listener: OnSelectedClickListener){
        selectedClickListener = listener
    }

    inner class FiltersListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val mTextFilters = itemView.txt_filters
        val mRadioButton = itemView.radiobtn_filters

        init {
            mRadioButton.setOnClickListener {
                mSelected = adapterPosition
                notifyDataSetChanged()
                selectedClickListener.selectPosition(mDataList[mSelected], 2)
            }
        }

        fun bind(model: FilterModel) {
            mTextFilters.text = model.name
        }
    }
}