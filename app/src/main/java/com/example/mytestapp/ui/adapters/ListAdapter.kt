package com.example.mytestapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.R
import com.example.mytestapp.data.model.CompanyModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_line_companies.view.*


class ListAdapter : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var mListOfRestaurants: List<CompanyModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View =
                layoutInflater.inflate(R.layout.item_line_companies, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(mListOfRestaurants[position])
    }

    override fun getItemCount(): Int {
        return mListOfRestaurants.size
    }

    fun setData(dataList: List<CompanyModel>) {
        mListOfRestaurants = dataList
        notifyDataSetChanged()
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val mNameTxt: TextView = itemView.name_txt
        private val mPhoto: ImageView = itemView.res_photo
        private val mDiscountBtn = itemView.discount_button
        private val mCreditCartImg = itemView.credit_card_img
        private val mDeliveryTxt: TextView = itemView.delivery_txt
        private val mRatingTxt = itemView.rating_txt
        private val mRatingLayout = itemView.rating_layout
        private val mNotEnoughRatingTxt = itemView.not_enough_rating_txt
        private val mTimeWorkingTxt = itemView.time_working_txt
        private val mStatusTxt = itemView.status_txt

        fun bind(model: CompanyModel) {
            mNameTxt.text = model.name
            mCreditCartImg.visibility = if (model.terminalPayment == 1) View.VISIBLE else View.GONE
            mDeliveryTxt.text = model.deliveryPriceText

            if (model.reviewsCount > 0)
                mRatingTxt.text = model.rating.toString()
            else {
                mRatingLayout.visibility = View.GONE
                mNotEnoughRatingTxt.visibility = View.VISIBLE
            }

            if (model.isWorkingNow == 0) {
                mStatusTxt.text = itemView.resources.getString(R.string.pre_order)
                mStatusTxt.setTextColor(mTimeWorkingTxt.currentTextColor)
            }

            when (model.status) {
                0 -> mTimeWorkingTxt.text = itemView.resources.getString(R.string.output)
                1 -> mTimeWorkingTxt.text = model.todayWorkingTimeText
                else -> {
                    mTimeWorkingTxt.text = itemView.resources.getString(R.string.pause)
                    mStatusTxt.visibility = View.GONE
                }
            }

            val count = model.actionInformerText.size
            val items: String = itemView.resources.getQuantityString(R.plurals.plurals_1, count, count)
            if (count == 0)
                mDiscountBtn.visibility = View.GONE
            else
                mDiscountBtn.text = items

            Picasso.with(itemView.context)
                    .load(model.image)
                    .fit()
                    .into(mPhoto)
        }
    }
}
