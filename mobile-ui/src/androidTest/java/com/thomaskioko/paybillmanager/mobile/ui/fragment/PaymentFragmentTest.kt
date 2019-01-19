package com.thomaskioko.paybillmanager.mobile.ui.fragment

import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.SimpleFragmentActivity
import com.thomaskioko.paybillmanager.mobile.util.EspressoAnimationTestUtil
import com.thomaskioko.paybillmanager.mobile.util.ViewModelUtil.createViewModelFactory
import com.thomaskioko.paybillmanager.presentation.model.CategoryView
import com.thomaskioko.paybillmanager.presentation.model.JengaTokenView
import com.thomaskioko.paybillmanager.presentation.model.MpesaPushResponseView
import com.thomaskioko.paybillmanager.presentation.state.Resource
import com.thomaskioko.paybillmanager.presentation.viewmodel.JengaRequestsViewModel
import com.thomaskioko.paybillmanager.presentation.viewmodel.bill.GetBillsViewModel
import com.thomaskioko.paybillmanager.presentation.viewmodel.billcategory.GetBillCategoryViewModel
import org.hamcrest.core.IsNot
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock


class PaymentFragmentTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SimpleFragmentActivity::class.java)

    private lateinit var jengaRequestViewModel: JengaRequestsViewModel
    private lateinit var getBillCategoryViewModel: GetBillCategoryViewModel
    private lateinit var getBillsViewModel: GetBillsViewModel

    private val liveData = MutableLiveData<Resource<JengaTokenView>>()
    private val mpesaStkLiveData = MutableLiveData<Resource<MpesaPushResponseView>>()
    private val categoryLiveData = MutableLiveData<Resource<CategoryView>>()


    fun init() {
        val fragment = PaymentFragment()

        jengaRequestViewModel = mock(JengaRequestsViewModel::class.java)
        getBillCategoryViewModel = mock(GetBillCategoryViewModel::class.java)
        getBillsViewModel = mock(GetBillsViewModel::class.java)

        `when`(jengaRequestViewModel.getJengaToken()).thenReturn(liveData)
        `when`(jengaRequestViewModel.liveData).thenReturn(liveData)
        `when`(jengaRequestViewModel.getMpesaPushResponse()).thenReturn(mpesaStkLiveData)
        `when`(jengaRequestViewModel.mpesaStkLiveData).thenReturn(mpesaStkLiveData)

        `when`(getBillCategoryViewModel.getCategoryByBillId()).thenReturn(categoryLiveData)
        `when`(getBillCategoryViewModel.categoryLiveData).thenReturn(categoryLiveData)

        fragment.viewModelFactory = createViewModelFactory(jengaRequestViewModel)
        fragment.viewModelFactory = createViewModelFactory(getBillCategoryViewModel)
        fragment.viewModelFactory = createViewModelFactory(getBillsViewModel)

        activityRule.activity.setFragment(fragment)
        activityRule.runOnUiThread { }

        EspressoAnimationTestUtil.disableProgressBarAnimations(activityRule)

    }

    fun onMoBileMoneyImageViewClickedInPutLayoutIsVisible() {

        //When mobile money icon is clicked
        onView(withId(R.id.iv_mobile_money)).perform(click())

        //Verify that Input layout is shown
        onView(withId(R.id.input_layout_phone_number)).check(matches(isDisplayed()))

    }

    fun onCardImageViewClickedInPutLayoutIsInvisible() {

        //When mobile card icon is clicked
        onView(withId(R.id.iv_card)).perform(click())

        //Verify that Input layout is not shown
        onView(withId(R.id.input_layout_phone_number)).check(matches(IsNot.not(isDisplayed())))
    }

}