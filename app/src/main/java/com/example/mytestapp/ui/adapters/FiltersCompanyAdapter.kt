package com.example.mytestapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.R
import com.example.mytestapp.data.model.AvailableCompaniesModel
import com.example.mytestapp.ui.interfaces.OnSelectedClickListener
import kotlinx.android.synthetic.main.item_line_filters_checkbox.view.*

class FiltersCompanyAdapter: RecyclerView.Adapter<FiltersCompanyAdapter.FilterCompanyViewHolder>() {

    private var mDataList: List<AvailableCompaniesModel> = ArrayList()
    lateinit var selectedClickListener: OnSelectedClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterCompanyViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View =
                layoutInflater.inflate(R.layout.item_line_filters_checkbox, parent, false)
        return FilterCompanyViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilterCompanyViewHolder, position: Int) {
        holder.bind(mDataList[position])
        holder. mCheckBox.setOnClickListener {
            selectedClickListener.selectPosition(mDataList[position])
        }
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    fun setData(dataList: List<AvailableCompaniesModel>) {
        mDataList = dataList
        notifyDataSetChanged()
    }

    fun setSelectedListener(listener: OnSelectedClickListener){
        selectedClickListener = listener
    }

    inner class FilterCompanyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val mText = itemView.text_filters_checkbox
        val mCheckBox = itemView.checkbox_filters

        init {

        }

        fun bind(model: AvailableCompaniesModel){
            mText.text = model.name
        }
    }
}