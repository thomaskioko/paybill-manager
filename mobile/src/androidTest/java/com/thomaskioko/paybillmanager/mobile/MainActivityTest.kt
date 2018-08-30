package com.thomaskioko.paybillmanager.mobile

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.rule.ActivityTestRule
import com.thomaskioko.paybillmanager.mobile.ui.MainActivity
import com.thomaskioko.paybillmanager.mobile.util.RecyclerViewMatcher
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun viewIsShown() {

        //Verify that the toolbar is displayed
        onView(allOf(ViewMatchers.withId(R.id.toolbar_main))).check(matches(isDisplayed()))

    }

}