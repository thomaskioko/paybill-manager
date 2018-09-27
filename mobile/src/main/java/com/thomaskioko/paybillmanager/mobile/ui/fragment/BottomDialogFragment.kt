package com.thomaskioko.paybillmanager.mobile.ui.fragment


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.injection.Injectable
import com.thomaskioko.paybillmanager.mobile.ui.adapter.DaysAdapter
import com.thomaskioko.paybillmanager.mobile.util.DateUtils
import kotlinx.android.synthetic.main.fragment_bottom_dialog.*
import javax.inject.Inject


class BottomDialogFragment : BottomSheetDialogFragment() , Injectable {

    @Inject
    lateinit var daysAdapter: DaysAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom_dialog, container, false)
    }

    @SuppressLint("NewApi")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recycler_view_dates.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        recycler_view_dates.adapter = daysAdapter

        daysAdapter.offsetDateTimeLists = DateUtils.getDaysOfWeek()
        daysAdapter.notifyDataSetChanged()


    }
}
