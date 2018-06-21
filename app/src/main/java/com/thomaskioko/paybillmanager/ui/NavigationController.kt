package com.thomaskioko.paybillmanager.ui

import com.thomaskioko.paybillmanager.R
import com.thomaskioko.paybillmanager.testing.OpenForTesting
import com.thomaskioko.paybillmanager.ui.activity.MainActivity
import com.thomaskioko.paybillmanager.ui.fragment.DashboardFragment
import javax.inject.Inject

/**
 * Class responsible for navigation
 */


@OpenForTesting
class NavigationController
@Inject constructor(private var mainActivity: MainActivity) {

    private val containerId = R.id.container
    private val fragmentManager = mainActivity.supportFragmentManager

    fun navigateToDashboardFragment() {
        val movieListFragment = DashboardFragment()
        fragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(containerId, movieListFragment)
                .addToBackStack("DashBoard")
                .commitAllowingStateLoss()
    }

}