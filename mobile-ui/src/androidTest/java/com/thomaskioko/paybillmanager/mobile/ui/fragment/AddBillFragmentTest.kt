package com.thomaskioko.paybillmanager.mobile.ui.fragment

import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.SimpleFragmentActivity
import com.thomaskioko.paybillmanager.mobile.ui.NavigationController
import com.thomaskioko.paybillmanager.mobile.util.EspressoAnimationTestUtil
import com.thomaskioko.paybillmanager.mobile.util.ViewModelUtil
import com.thomaskioko.paybillmanager.presentation.model.BillView
import com.thomaskioko.paybillmanager.presentation.model.CategoryView
import com.thomaskioko.paybillmanager.presentation.state.Resource
import com.thomaskioko.paybillmanager.presentation.viewmodel.SharedViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class AddBillFragmentTest {


    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SimpleFragmentActivity::class.java, true, true)

    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var navigationController: NavigationController

    private val stringLiveData = MutableLiveData<String>()
    private val liveData = MutableLiveData<Resource<List<CategoryView>>>()
    private val billViewLiveData = MutableLiveData<Resource<BillView>>()

    @Before
    fun init() {
        val fragment = BillAmountFragment()

        navigationController = Mockito.mock(NavigationController::class.java)

        sharedViewModel = Mockito.mock(SharedViewModel::class.java)

        Mockito.`when`(sharedViewModel.categoriesLiveData).thenReturn(liveData)
        Mockito.`when`(sharedViewModel.billViewLiveData).thenReturn(billViewLiveData)

        Mockito.`when`(sharedViewModel.getCategories()).thenReturn(liveData)
        Mockito.`when`(sharedViewModel.getAmount()).thenReturn(stringLiveData)
        Mockito.`when`(sharedViewModel.getCategoryId()).thenReturn(stringLiveData)

        fragment.viewModelFactory = ViewModelUtil.createViewModelFactory(sharedViewModel)
        activityRule.activity.setFragment(fragment)

        activityRule.runOnUiThread {  }
        EspressoAnimationTestUtil.disableProgressBarAnimations(activityRule)

    }


    @Test
    fun bill_amount_entered() {

        onView(withId(R.id.tv_bill_amount)).check(matches(withText("0")))

    }


}
