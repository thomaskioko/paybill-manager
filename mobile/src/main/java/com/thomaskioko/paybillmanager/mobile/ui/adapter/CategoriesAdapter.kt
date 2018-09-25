package com.thomaskioko.paybillmanager.mobile.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.thomaskioko.paybillmanager.domain.model.Category
import com.thomaskioko.paybillmanager.mobile.R
import javax.inject.Inject

class CategoriesAdapter @Inject constructor() : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    var categories: List<Category> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_category, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return categories.count()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]

        holder.fabButton.setImageDrawable(holder.context.resources.getDrawable(category.drawableUrl))
        holder.itemView.setOnClickListener {
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var context = view.context!!
        var fabButton: FloatingActionButton = view.findViewById(R.id.fab_icon_category)

    }

}