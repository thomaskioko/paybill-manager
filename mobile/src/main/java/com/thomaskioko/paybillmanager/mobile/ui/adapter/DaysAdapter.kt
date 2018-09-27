package com.thomaskioko.paybillmanager.mobile.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.thomaskioko.paybillmanager.mobile.R
import kotlinx.android.synthetic.main.item_date.view.*
import org.threeten.bp.OffsetDateTime
import javax.inject.Inject


class DaysAdapter @Inject constructor() : RecyclerView.Adapter<DaysAdapter.ViewHolder>() {

    var offsetDateTimeLists: List<OffsetDateTime> = arrayListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_date, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return offsetDateTimeLists.count()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val offsetDateTime = offsetDateTimeLists[position]

        val day = offsetDateTime.dayOfWeek.name

        holder.tvDay.text = offsetDateTime.dayOfWeek.name.substring(0, Math.min(day.length, 3))
        holder.tvDate.text = offsetDateTime.dayOfMonth.toString()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvDate = view.tv_date!!
        var tvDay = view.tv_day!!
    }

}