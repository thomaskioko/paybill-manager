package com.thomaskioko.paybillmanager.mobile.ui.fragment


import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.injection.Injectable
import com.thomaskioko.paybillmanager.mobile.ui.adapter.DaysAdapter
import com.thomaskioko.paybillmanager.mobile.util.DateUtils
import kotlinx.android.synthetic.main.fragment_bottom_dialog.*
import org.threeten.bp.OffsetDateTime


class BillDetailsBottomDialogFragment : BottomSheetDialogFragment(), Injectable, DaysAdapter.OnRecyclerViewItemClickListener {


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

    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog?, style: Int) {
        super.setupDialog(dialog, style)

        activity!!.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        dialog!!.setOnShowListener {
            val handler = Handler()
            handler.postDelayed({
                val bottomSheetDialog = dialog as BottomSheetDialog
                val bottomSheet = bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout?
                BottomSheetBehavior.from(bottomSheet!!).state = BottomSheetBehavior.STATE_EXPANDED
            }, 250)
        }
    }

    override fun selectedDateItem(offsetDateTime: OffsetDateTime) {
    }

    companion object {
        const val BUNDLE_AMOUNT = "bundle_amount"
        const val BUNDLE_CATEGORY_ID = "bundle_category_id"
    }
}
