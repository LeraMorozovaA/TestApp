package com.example.mytestapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.R
import com.example.mytestapp.data.model.AvailableDeliveryModel
import com.example.mytestapp.ui.interfaces.OnSelectedClickListener
import com.example.mytestapp.util.setIcon
import com.example.mytestapp.util.setTitle
import kotlinx.android.synthetic.main.item_line_filters_img_radiobutton.view.*

class DeliveryListAdapter: RecyclerView.Adapter<DeliveryListAdapter.DeliveryListViewHolder>(){

    private var mDataList: List<AvailableDeliveryModel> = ArrayList()
    lateinit var selectedClickListener:  OnSelectedClickListener
    var mSelected = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return  DeliveryListViewHolder(inflater.inflate(R.layout.item_line_filters_img_radiobutton, parent, false))
        }

    override fun onBindViewHolder(holder: DeliveryListViewHolder, position: Int) {
        holder.bind(mDataList[position])
        holder.mRadioButton.setChecked(mSelected == position)
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    fun setData(dataList: List<AvailableDeliveryModel>) {
        mDataList = dataList
        notifyDataSetChanged()
    }

    fun setSelectedListener(listener: OnSelectedClickListener){
        selectedClickListener = listener
    }

    inner class DeliveryListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val mImage = itemView.img_filters_rbtn
        val mRadioButton = itemView.radiobutton_filters

        init {
            mRadioButton.setOnClickListener {
                mSelected = adapterPosition
                notifyDataSetChanged()
                selectedClickListener.selectPosition(mDataList[mSelected])
            }
        }

        fun bind(model: AvailableDeliveryModel){
            mImage.setIcon(type = model.type)
            mRadioButton.setTitle(model = model)
        }
    }
}