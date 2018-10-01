package com.thomaskioko.paybillmanager.mobile.ui.fragment

import androidx.appcompat.widget.Toolbar
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.SimpleFragmentActivity
import com.thomaskioko.paybillmanager.mobile.factory.BillsDataFactory
import com.thomaskioko.paybillmanager.mobile.test.TestApplication
import com.thomaskioko.paybillmanager.mobile.ui.NavigationController
import com.thomaskioko.paybillmanager.mobile.ui.adapter.BillsAdapter
import com.thomaskioko.paybillmanager.presentation.model.BillView
import com.thomaskioko.paybillmanager.presentation.state.Resource
import com.thomaskioko.paybillmanager.presentation.state.ResourceState
import com.thomaskioko.paybillmanager.presentation.viewmodel.GetBillsViewModel
import com.thomaskioko.xapotest.util.ViewModelUtil
import io.reactivex.Observable
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.core.IsNot
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito


class BillsListFragmentTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SimpleFragmentActivity::class.java, true, true)

    private lateinit var viewModel: GetBillsViewModel

    private val liveData = MutableLiveData<Resource<List<BillView>>>()

    private lateinit var navigationController: NavigationController

    @Before
    fun init() {
        val fragment = BillsListFragment()

        viewModel = Mockito.mock(GetBillsViewModel::class.java)
        Mockito.`when`(viewModel.getBills()).thenReturn(liveData)

        navigationController = Mockito.mock(NavigationController::class.java)

        fragment.viewModelFactory = ViewModelUtil.createFor(viewModel)
        activityRule.activity.setFragment(fragment)

        navigationController = Mockito.mock(NavigationController::class.java)
    }

    @Test
    fun onStart_toolBarTitleIsCorrect() {

        onView(withId(R.id.toolbar_bill_list)).check(matches(isDisplayed()))
        matchToolbarTitle("PayBill Manager")

    }


    @Test
    fun onDataLoading_progressBarIsDisplayed() {

        liveData.postValue(Resource(ResourceState.LOADING, null, null))

        //Verify that the progressbar is displayed
        Espresso.onView(withId(R.id.progress_bar)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun onDataLoaded_progressBarNotDisplayed() {

        liveData.postValue(Resource(ResourceState.SUCCESS, null, null))

        //Verify that the progressbar is not displayed
        Espresso.onView(withId(R.id.progress_bar)).check(ViewAssertions.matches(IsNot.not(ViewMatchers.isDisplayed())))
    }

    @Test
    fun onDataLoaded_favoriteProjectsDisplay() {

        val billsList = listOf(BillsDataFactory.makeBillView(),
                BillsDataFactory.makeBillView(), BillsDataFactory.makeBillView())

        liveData.postValue(Resource(ResourceState.SUCCESS, billsList, null))

        billsList.forEachIndexed { index, bill ->
            onView(withId(R.id.recycler_view_add_bills))
                    .perform(RecyclerViewActions.scrollToPosition<BillsAdapter.ViewHolder>(index))

            onView(withId(R.id.recycler_view_add_bills))
                    .check(matches(hasDescendant(withText(bill.billName))))
        }
    }


    private fun matchToolbarTitle(title: CharSequence): ViewInteraction {
        return onView(isAssignableFrom(Toolbar::class.java))
                .check(matches(withToolbarTitle(`is`(title))))
    }

    private fun withToolbarTitle(textMatcher: Matcher<CharSequence>): Matcher<Any> {
        return object : BoundedMatcher<Any, Toolbar>(Toolbar::class.java) {
            public override fun matchesSafely(toolbar: Toolbar): Boolean {
                return textMatcher.matches(toolbar.title)
            }

            override fun describeTo(description: Description) {
                description.appendText("with toolbar title: ")
                textMatcher.describeTo(description)
            }
        }
    }

}