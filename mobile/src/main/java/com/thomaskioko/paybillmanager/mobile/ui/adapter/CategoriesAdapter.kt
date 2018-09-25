package com.thomaskioko.paybillmanager.mobile.ui.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.thomaskioko.paybillmanager.domain.model.Category
import com.thomaskioko.paybillmanager.mobile.R
import javax.inject.Inject


class CategoriesAdapter @Inject constructor() : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    var categoriesList: List<Category> = arrayListOf()
    lateinit var selectionTracker: SelectionTracker<Long>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_category, parent, false)
        return ViewHolder(itemView)
    }

    fun setRecyclerSelectionTracker(tracker: SelectionTracker<Long>) {
        selectionTracker = tracker
    }

    override fun getItemCount(): Int {
        return categoriesList.count()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categoriesList[position]
        val key = getItemId(position)

        val selected = selectionTracker.isSelected(key)

        if (selected) {
            holder.fabButton.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(holder.context,
                    R.color.colorPrimaryDark))
        } else {
            holder.fabButton.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(holder.context,
                    R.color.white))
        }

        holder.fabButton.setImageDrawable(holder.context.resources.getDrawable(category.drawableUrl))
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var context = view.context!!
        var fabButton: FloatingActionButton = view.findViewById(R.id.fab_icon_category)

    }


    fun setData(categories: List<Category>) {
        categoriesList = categories
        notifyDataSetChanged()
    }
}