package com.thomaskioko.paybillmanager.mobile.ui.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.extension.inflate
import com.thomaskioko.paybillmanager.mobile.ui.util.NumberFormatter.formatNumber
import kotlinx.android.synthetic.main.item_bill.view.*
import javax.inject.Inject


class BillsAdapter @Inject constructor() : RecyclerView.Adapter<BillsAdapter.ViewHolder>() {

    var billsList: List<Bill> = arrayListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_bill))
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
        holder.tvBillNumber.text = holder.context.getString(R.string.placeholder_bill_number, bill.paybillNumber)
        holder.tvBillAmount.text = holder.context.getString(R.string.placeholder_bill_amount, formatNumber(bill.amount))
        holder.tvBillAccountNumber.text = holder.context.getString(R.string.placeholder_bill_account, bill.accountNumber)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var context = view.context!!
        var tvBillName: TextView = view.tv_bill_name
        var tvBillNumber: TextView = view.tv_bill_paybill
        var tvBillAmount: TextView = view.tv_bill_amount
        var tvBillAccountNumber: TextView = view.tv_bill_account_number

    }

}
