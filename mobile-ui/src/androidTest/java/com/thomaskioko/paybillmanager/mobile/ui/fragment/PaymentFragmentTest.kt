package com.thomaskioko.paybillmanager.mobile.ui.fragment

import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.SimpleFragmentActivity
import com.thomaskioko.paybillmanager.mobile.util.EspressoAnimationTestUtil
import com.thomaskioko.paybillmanager.mobile.util.ViewModelUtil
import com.thomaskioko.paybillmanager.presentation.model.JengaTokenView
import com.thomaskioko.paybillmanager.presentation.state.Resource
import com.thomaskioko.paybillmanager.presentation.viewmodel.JengaRequestsViewModel
import org.hamcrest.core.IsNot
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class PaymentFragmentTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SimpleFragmentActivity::class.java, true, true)

    private lateinit var viewModel: JengaRequestsViewModel

    private val liveData = MutableLiveData<Resource<JengaTokenView>>()


    @Before
    fun init() {
        val fragment = PaymentFragment()

        viewModel = Mockito.mock(JengaRequestsViewModel::class.java)
        Mockito.`when`(viewModel.getJengaToken()).thenReturn(liveData)
        Mockito.`when`(viewModel.liveData).thenReturn(liveData)



        fragment.viewModelFactory = ViewModelUtil.createViewModelFactory(viewModel)
        activityRule.activity.setFragment(fragment)

        activityRule.runOnUiThread { }

        EspressoAnimationTestUtil.disableProgressBarAnimations(activityRule)

    }

    @Test
    fun onMoBileMoneyImageViewClickedInPutLayoutIsVisible(){

        //When mobile money icon is clicked
        onView(withId(R.id.iv_mobile_money)).perform(click())

        //Verify that Input layout is shown
        onView(withId(R.id.input_layout_phone_number)).check(matches(isDisplayed()))

    }

    @Test
    fun onCardImageViewClickedInPutLayoutIsInvisible(){

        //When mobile card icon is clicked
        onView(withId(R.id.iv_card)).perform(click())

        //Verify that Input layout is not shown
        onView(withId(R.id.input_layout_phone_number)).check(matches(IsNot.not(isDisplayed())))
    }

}