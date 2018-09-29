package com.thomaskioko.paybillmanager.mobile.ui.fragment

import android.app.Activity
import android.view.Gravity
import androidx.appcompat.widget.Toolbar
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.DrawerMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.ui.MainActivity
import org.junit.Rule
import org.junit.Test


class AddBillFragmentTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun clickDrawerIconOpensAndClosesNavigationDrawer() {
        Espresso.onView(withId(R.id.material_drawer_layout)).check(ViewAssertions.matches(DrawerMatchers.isClosed(Gravity.START)))

        Espresso.onView(ViewMatchers.withContentDescription(getToolbarNavigationContentDescription(
                activityTestRule.activity, R.id.toolbar_bill_list))).perform(ViewActions.click())
    }


}


/**
 * Returns the content description for the navigation button view in the toolbar.
 */
fun getToolbarNavigationContentDescription(activity: Activity, toolbarId: Int) =
        activity.findViewById<Toolbar>(toolbarId).navigationContentDescription as String