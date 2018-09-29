package com.thomaskioko.paybillmanager.mobile.ui.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.domain.model.Category
import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.extension.inflate
import kotlinx.android.synthetic.main.item_upcoming_bill.view.*
import javax.inject.Inject


class BillsAdapter @Inject constructor() : RecyclerView.Adapter<BillsAdapter.ViewHolder>() {

    var billsList: List<Bill> = arrayListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_upcoming_bill))
    }

    override fun getItemCount(): Int {
        return billsList.count()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bill = billsList[position]

        holder.tvBillName.text = bill.billName
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var context = view.context!!
        var tvBillName: TextView = view.tv_bill_name

    }

}