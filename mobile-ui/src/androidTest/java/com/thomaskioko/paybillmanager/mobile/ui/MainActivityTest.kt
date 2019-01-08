package com.thomaskioko.paybillmanager.mobile.ui

import android.app.Activity
import android.view.Gravity
import androidx.appcompat.widget.Toolbar
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerMatchers
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.ui.activity.MainActivity
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    fun clickDrawerIconOpensAndClosesNavigationDrawer() {

        onView(withId(R.id.material_drawer_layout))
                .check(matches(DrawerMatchers.isClosed(Gravity.START)))

        onView(withContentDescription(getToolbarNavigationContentDescription(
                activityTestRule.activity, R.id.toolbar_bill_list)))
                .perform(ViewActions.click())
    }
}


/**
 * Returns the content description for the navigation button view in the toolbar.
 */
fun getToolbarNavigationContentDescription(activity: Activity, toolbarId: Int) =
        activity.findViewById<Toolbar>(toolbarId).navigationContentDescription as String