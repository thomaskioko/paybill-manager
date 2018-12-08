package com.thomaskioko.paybillmanager.mobile.ui

import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.ui.activity.MainActivity
import com.thomaskioko.paybillmanager.mobile.ui.fragment.*
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

    fun navigateToBillDetailFragment() {
        val fragment = BillDetailFragment()
        fragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(containerId, fragment)
                .addToBackStack("BillDetailFragment")
                .commit()
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
