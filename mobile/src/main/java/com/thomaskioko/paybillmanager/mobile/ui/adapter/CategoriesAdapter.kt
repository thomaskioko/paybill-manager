package com.thomaskioko.paybillmanager.mobile.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.thomaskioko.paybillmanager.domain.model.Category
import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.extension.inflate
import javax.inject.Inject


class CategoriesAdapter @Inject constructor() : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    var categoriesList: List<Category> = arrayListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_category))
    }

    override fun getItemCount(): Int {
        return categoriesList.count()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categoriesList[position]

        holder.fabButton.setImageDrawable(AppCompatResources.getDrawable(holder.context, category.drawableUrl))
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var context = view.context!!
        var fabButton: FloatingActionButton = view.findViewById(R.id.fab_icon_category)

    }

}