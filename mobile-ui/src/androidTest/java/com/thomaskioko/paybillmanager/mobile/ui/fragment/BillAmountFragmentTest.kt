package com.thomaskioko.paybillmanager.mobile.ui.fragment

import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.SimpleFragmentActivity
import com.thomaskioko.paybillmanager.mobile.ui.NavigationController
import com.thomaskioko.paybillmanager.mobile.util.EspressoAnimationTestUtil
import com.thomaskioko.paybillmanager.mobile.util.ViewModelUtil
import com.thomaskioko.paybillmanager.presentation.model.CategoryView
import com.thomaskioko.paybillmanager.presentation.state.Resource
import com.thomaskioko.paybillmanager.presentation.util.SingleLiveData
import com.thomaskioko.paybillmanager.presentation.viewmodel.SharedViewModel
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class BillAmountFragmentTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SimpleFragmentActivity::class.java, true, true)

    private lateinit var navigationController: NavigationController
    private lateinit var sharedViewModel: SharedViewModel

    private val categoriesLiveData = MutableLiveData<Resource<List<CategoryView>>>()
    private val amountLiveData = SingleLiveData<String>()


    @Before
    fun init() {
        val fragment = BillAmountFragment()

        navigationController = mock(NavigationController::class.java)

        sharedViewModel = mock(SharedViewModel::class.java)
        Mockito.`when`(sharedViewModel.categoriesLiveData).thenReturn(categoriesLiveData)
        Mockito.`when`(sharedViewModel.getCategories()).thenReturn(categoriesLiveData)
        Mockito.`when`(sharedViewModel.getAmount()).thenReturn(amountLiveData)

        fragment.viewModelFactory = ViewModelUtil.createViewModelFactory(sharedViewModel)

        activityRule.activity.setFragment(fragment)

        activityRule.runOnUiThread {

        }

        EspressoAnimationTestUtil.disableProgressBarAnimations(activityRule)
    }

    @Test
    fun amountDefaultText_IsZero() {
        onView(withId(R.id.tv_bill_amount)).check(matches(withText("0")))

        //Verify that the delete button is disabled
        onView(withId(R.id.btn_delete)).check(matches(not(isEnabled())))
    }

    @Test
    fun calculatorPadClicked_AmountIsCorrect(){
        //when keypad one is clicked
        onView(withId(R.id.tv_t9_key_1)).perform(click())

        //verify that the text is 1
        onView(withId(R.id.tv_bill_amount)).check(matches(withText("1")))

        //when keypad nine is clicked
        onView(withId(R.id.tv_t9_key_9)).perform(click())

        //verify that the text is 19
        onView(withId(R.id.tv_bill_amount)).check(matches(withText("19")))
    }

    @Test
    fun onDeleteClicked_TextIsSetCorrectly() {

        //when keypad one is clicked
        onView(withId(R.id.tv_t9_key_1)).perform(click())

        //verify that the text is 1
        onView(withId(R.id.tv_bill_amount)).check(matches(withText("1")))

        //when keypad nine is clicked
        onView(withId(R.id.tv_t9_key_9)).perform(click())

        //when keypad Five is clicked
        onView(withId(R.id.tv_t9_key_5)).perform(click())

        //When delete is clicked
        onView(withId(R.id.btn_delete)).perform(click())

        //verify that the text is 19
        onView(withId(R.id.tv_bill_amount)).check(matches(withText("19")))

    }


}