package com.thomaskioko.paybillmanager.mobile.ui

import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.ui.activity.MainActivity
import com.thomaskioko.paybillmanager.mobile.ui.fragment.BillsListFragment
import com.thomaskioko.paybillmanager.mobile.ui.fragment.MaterialStepperFragment
import com.thomaskioko.paybillmanager.mobile.ui.fragment.PaymentFragment
import com.thomaskioko.paybillmanager.mobile.ui.util.RevealAnimationSettings
import javax.inject.Inject


/**
 * Class responsible for navigating between views
 */
open class NavigationController @Inject constructor(mainActivity: MainActivity) {

    private val containerId = R.id.container
    private val fragmentManager = mainActivity.supportFragmentManager

    fun navigateToBillsListFragment() {
        val fragment = BillsListFragment()
        fragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(containerId, fragment)
                .addToBackStack("BillsListFragment")
                .commit()
    }

    fun navigateToPaymentFragment(billId: String, categoryId: String) {
        val bottomSheetDialogFragment = PaymentFragment.newInstance(billId, categoryId)
        bottomSheetDialogFragment.show(fragmentManager, bottomSheetDialogFragment.tag)
    }

    fun navigateToMaterialStepperFragment(revealSettings: RevealAnimationSettings) {
        val fragment = MaterialStepperFragment.newInstance(revealSettings)
        fragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(containerId, fragment)
                .addToBackStack("MaterialStepperFragment")
                .commit()

    }
}
