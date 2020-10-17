package com.pranoppal.yearpicker.ui.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pranoppal.yearpicker.R
import com.pranoppal.yearpicker.utils.inflate
import kotlinx.android.synthetic.main.item_year_text.view.*

class YearListAdapter(private val yearList: ArrayList<Int>): RecyclerView.Adapter<YearListAdapter.YearHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YearListAdapter.YearHolder {
        val inflatedView = parent.inflate(R.layout.item_year_text, false)
        return YearHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: YearHolder, position: Int) {
        holder.bind(yearList[position])
    }

    override fun getItemCount(): Int {
        return yearList.size
    }

    class YearHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(year: Int) {
            itemView.tv_year_text.text = year.toString()
        }

    }
}

