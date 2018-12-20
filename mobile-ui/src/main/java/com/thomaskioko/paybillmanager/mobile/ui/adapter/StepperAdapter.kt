package com.thomaskioko.paybillmanager.mobile.ui.adapter

import android.content.Context
import androidx.annotation.IntRange
import androidx.annotation.NonNull
import androidx.fragment.app.FragmentManager
import com.stepstone.stepper.Step
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter
import com.stepstone.stepper.viewmodel.StepViewModel
import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.ui.fragment.BillAmountFragment
import com.thomaskioko.paybillmanager.mobile.ui.fragment.BillDetailsFragment
import com.thomaskioko.paybillmanager.mobile.ui.fragment.ConfirmBillFragment


class StepperAdapter(fragmentManager: FragmentManager, context: Context) :
        AbstractFragmentStepAdapter(fragmentManager, context) {

    override fun createStep(position: Int): Step {
        return when (position) {
            0 -> BillAmountFragment()
            1 -> BillDetailsFragment()
            2 -> ConfirmBillFragment()
            else -> BillAmountFragment()
        }
    }

    override fun getCount(): Int {
        return 3
    }

    @NonNull
    override fun getViewModel(@IntRange(from = 0) position: Int): StepViewModel {
        //Override this method to set Step title for the Tabs, not necessary for other stepper types
        val title = when (position) {
            0 -> context.resources.getString(R.string.toolbar_new_bill)
            1 -> context.resources.getString(R.string.toolbar_bill_details)
            2 -> context.resources.getString(R.string.toolbar_confirm_details)
            else -> context.resources.getString(R.string.app_name)
        }
        return StepViewModel.Builder(context)
                .setTitle(title)
                .create()
    }
}