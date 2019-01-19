package com.thomaskioko.paybillmanager.mobile.ui.fragment

import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.SimpleFragmentActivity
import com.thomaskioko.paybillmanager.mobile.TestApplication
import com.thomaskioko.paybillmanager.mobile.factory.BillsDataFactory
import com.thomaskioko.paybillmanager.mobile.mapper.BillsViewMapper
import com.thomaskioko.paybillmanager.mobile.ui.NavigationController
import com.thomaskioko.paybillmanager.mobile.ui.adapter.BillsAdapter
import com.thomaskioko.paybillmanager.mobile.util.EspressoAnimationTestUtil
import com.thomaskioko.paybillmanager.mobile.util.ViewModelUtil
import com.thomaskioko.paybillmanager.mobile.util.matcher.ToolbarViewMarcher.matchToolbarTitle
import com.thomaskioko.paybillmanager.presentation.model.BillView
import com.thomaskioko.paybillmanager.presentation.state.Resource
import com.thomaskioko.paybillmanager.presentation.state.ResourceState
import com.thomaskioko.paybillmanager.presentation.viewmodel.bill.GetBillsViewModel
import io.reactivex.Flowable
import org.hamcrest.CoreMatchers.not
import org.hamcrest.core.IsNot
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class BillsListFragmentTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SimpleFragmentActivity::class.java, true, true)

    private lateinit var viewModel: GetBillsViewModel
    private lateinit var navigationController: NavigationController

    private val billsLiveData = MutableLiveData<Resource<List<BillView>>>()
    private val billLiveData = MutableLiveData<Resource<BillView>>()

    @Before
    fun init() {
        val fragment = BillsListFragment()

        navigationController = mock(NavigationController::class.java)


        viewModel = Mockito.mock(GetBillsViewModel::class.java)
        `when`(viewModel.getBills()).thenReturn(billsLiveData)
        `when`(viewModel.billsLiveData).thenReturn(billsLiveData)
        `when`(viewModel.getBill()).thenReturn(billLiveData)
        `when`(viewModel.billLiveData).thenReturn(billLiveData)


        fragment.viewModelFactory = ViewModelUtil.createViewModelFactory(viewModel)
        fragment.adapter = BillsAdapter()
        fragment.mapper = BillsViewMapper()
        activityRule.activity.setFragment(fragment)

        activityRule.runOnUiThread { }
        EspressoAnimationTestUtil.disableProgressBarAnimations(activityRule)
    }

    @Test
    fun onStart_toolBarTitleIsCorrect() {

        onView(withId(R.id.toolbar_bill_list)).check(matches(isDisplayed()))
        matchToolbarTitle(activityRule.activity.resources.getString(R.string.app_name))

    }


    fun onDataLoading_progressBarIsDisplayed() {

        billsLiveData.postValue(Resource(ResourceState.LOADING, null, null))

        //Verify that the progressbar is displayed
        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()))
    }

    fun onDataLoaded_progressBarNotDisplayed() {

        billsLiveData.postValue(Resource(ResourceState.SUCCESS, null, null))

        //Verify that the progressbar is not displayed
        onView(withId(R.id.progress_bar)).check(matches(IsNot.not(isDisplayed())))
    }

    @Test
    fun onDataLoaded_BillLisIsDisplayed() {

        val billsList = listOf(BillsDataFactory.makeBillView(),
                BillsDataFactory.makeBillView(), BillsDataFactory.makeBillView())

        billsLiveData.postValue(Resource(ResourceState.SUCCESS, billsList, null))

        //Loop through the list and populate the list with data
        billsList.forEachIndexed { index, bill ->
            onView(withId(R.id.recycler_view_bill_list))
                    .perform(RecyclerViewActions.scrollToPosition<BillsAdapter.ViewHolder>(index))

            //verify that the
            onView(withId(R.id.recycler_view_bill_list))
                    .check(matches(hasDescendant(withText(bill.billName))))
        }
    }

    @Test
    fun onBillIsClickable() {

        val billsList = listOf(BillsDataFactory.makeBillView(),
                BillsDataFactory.makeBillView(), BillsDataFactory.makeBillView())

        billsLiveData.postValue(Resource(ResourceState.SUCCESS, billsList, null))

        //Verify the progressbar is not visible
        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())))

        //Check if a view item is clickable
        //onView(withId(R.id.recycler_view_bill_list)).perform(click())

    }

    private fun stubBillsRepository(single: Flowable<List<Bill>>) {
        whenever(TestApplication.appComponent().billsBillsRepository().getBills())
                .thenReturn(single)
    }

}