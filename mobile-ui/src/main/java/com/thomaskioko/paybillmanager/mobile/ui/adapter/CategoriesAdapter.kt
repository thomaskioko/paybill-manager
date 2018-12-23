package com.thomaskioko.paybillmanager.mobile.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.thomaskioko.paybillmanager.domain.model.Category
import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.extension.inflate
import kotlinx.android.synthetic.main.item_category.view.*
import javax.inject.Inject


class CategoriesAdapter @Inject constructor(
        private val recyclerViewItemClickListener: OnRecyclerViewItemClickListener
) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    var categoriesList: List<Category> = arrayListOf()
    private var lastSelectedPosition = -1


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

        holder.imageDrawable.background = ResourcesCompat.getDrawable(
                holder.context.resources, category.drawableUrl, null
        )

        if (lastSelectedPosition == position) {
            holder.imageBackground.background = ResourcesCompat.getDrawable(
                    holder.context.resources, R.drawable.background_circle_dark, null
            )
        } else {
            holder.imageBackground.background = ResourcesCompat.getDrawable(
                    holder.context.resources, R.drawable.background_circle_light, null
            )
        }


        holder.imageBackground.setOnClickListener {
            lastSelectedPosition = holder.adapterPosition
            notifyDataSetChanged()

            recyclerViewItemClickListener.selectedCategoryItem(category)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var context = view.context!!
        val layout = view.layout_item!!
        val imageBackground = view.image_button_background!!
        val imageDrawable = view.image_button_drawable!!

    }

    interface OnRecyclerViewItemClickListener {
        fun selectedCategoryItem(category: Category)
    }

}
