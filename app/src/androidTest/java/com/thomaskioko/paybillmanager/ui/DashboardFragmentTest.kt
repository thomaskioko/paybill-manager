package com.thomaskioko.paybillmanager.ui

import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.thomaskioko.paybillmanager.testing.ui.SingleFragmentActivity
import com.thomaskioko.paybillmanager.util.TaskExecutorWithIdlingResourceRule
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DashboardFragmentTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java, true, true)

    @Rule
    @JvmField
    val executorRule = TaskExecutorWithIdlingResourceRule()

}