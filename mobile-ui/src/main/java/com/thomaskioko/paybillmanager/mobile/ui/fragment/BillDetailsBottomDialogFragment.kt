package com.thomaskioko.paybillmanager.mobile.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.injection.Injectable
import com.thomaskioko.paybillmanager.mobile.ui.adapter.DaysAdapter
import com.thomaskioko.paybillmanager.mobile.util.DateUtils
import kotlinx.android.synthetic.main.fragment_bottom_dialog.*
import org.threeten.bp.OffsetDateTime


class BillDetailsBottomDialogFragment : BottomSheetDialogFragment(), Injectable, DaysAdapter.OnRecyclerViewItemClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom_dialog, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val adapter = DaysAdapter(this)

        recycler_view_dates.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        recycler_view_dates.adapter = adapter

        adapter.offsetDateTimeLists = DateUtils.getDaysOfWeek()
        adapter.notifyDataSetChanged()

    }


    override fun selectedDateItem(offsetDateTime: OffsetDateTime) {
    }
}
