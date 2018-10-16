package com.thomaskioko.paybillmanager.mobile.ui.fragment

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.SimpleFragmentActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BillDetailsBottomDialogFragmentTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SimpleFragmentActivity::class.java, true, true)


    @Before
    fun init() {
        val fragment = BillDetailsBottomDialogFragment()

        activityRule.activity.setFragment(fragment)
    }

    @Test
    fun testEditTextViews() {

        onView(withId(R.id.ti_bill_label))
                .perform(typeText("Zuku"), closeSoftKeyboard())

        onView(withId(R.id.ti_bill_number))
                .perform(typeText("245245"), closeSoftKeyboard())
    }
}