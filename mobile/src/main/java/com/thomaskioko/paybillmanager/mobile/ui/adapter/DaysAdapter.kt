package com.thomaskioko.paybillmanager.mobile.ui.adapter

import android.util.SparseBooleanArray
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.extension.inflate
import kotlinx.android.synthetic.main.item_date.view.*
import org.threeten.bp.OffsetDateTime
import javax.inject.Inject


class DaysAdapter @Inject constructor(
        private val recyclerViewItemClickListener: OnRecyclerViewItemClickListener
) : RecyclerView.Adapter<DaysAdapter.ViewHolder>() {

    var offsetDateTimeLists: List<OffsetDateTime> = arrayListOf()
    var sparseBooleanArray: SparseBooleanArray = SparseBooleanArray()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_date))
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

        if (sparseBooleanArray[position]) {
            holder.layoutDate.background = ResourcesCompat.getDrawable(holder.context.resources, R.drawable.background_dark, null)
            holder.tvDate.setTextColor(ResourcesCompat.getColor(holder.context.resources, R.color.white, null))
            holder.tvDay.setTextColor(ResourcesCompat.getColor(holder.context.resources, R.color.white, null))

        } else {
            holder.layoutDate.background = ResourcesCompat.getDrawable(holder.context.resources, R.drawable.background_light, null)
            holder.tvDay.setTextColor(ResourcesCompat.getColor(holder.context.resources, R.color.colorPrimaryDark, null))
            holder.tvDate.setTextColor(ResourcesCompat.getColor(holder.context.resources, R.color.secondary_text, null))
        }

        holder.itemView.setOnClickListener {

            if (!sparseBooleanArray.get(holder.adapterPosition)) {
                sparseBooleanArray.put(holder.adapterPosition, true)
                notifyItemChanged(holder.adapterPosition)
            } else {
                sparseBooleanArray.put(holder.adapterPosition, false)
                notifyItemChanged(holder.adapterPosition)
            }

            recyclerViewItemClickListener.selectedDateItem(offsetDateTime)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val context = view.context!!
        val tvDate = view.tv_date!!
        val tvDay = view.tv_day!!
        val layoutDate = view.cl_date!!

    }

    interface OnRecyclerViewItemClickListener {
        fun selectedDateItem(offsetDateTime: OffsetDateTime)
    }
}
