package com.thomaskioko.paybillmanager.mobile.ui.fragment

import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.SimpleFragmentActivity
import com.thomaskioko.paybillmanager.mobile.ui.NavigationController
import com.thomaskioko.paybillmanager.mobile.util.EspressoTextInputLayoutUtils.withErrorInInputLayout
import com.thomaskioko.paybillmanager.mobile.util.ViewModelUtil
import com.thomaskioko.paybillmanager.presentation.model.BillView
import com.thomaskioko.paybillmanager.presentation.state.Resource
import com.thomaskioko.paybillmanager.presentation.viewmodel.CreateBillsViewModel
import com.thomaskioko.paybillmanager.mobile.util.EspressoAnimationTestUtil
import org.hamcrest.CoreMatchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class BillDetailsBottomDialogFragmentTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SimpleFragmentActivity::class.java, true, true)

    private lateinit var navigationController: NavigationController
    private lateinit var viewModel: CreateBillsViewModel
    private val liveData = MutableLiveData<String>()
    private val billLiveData = MutableLiveData<Resource<BillView>>()

    @Before
    fun init() {
        val fragment = BillDetailsBottomDialogFragment()

        navigationController = Mockito.mock(NavigationController::class.java)

        viewModel = Mockito.mock(CreateBillsViewModel::class.java)
        Mockito.`when`(viewModel.getAmount()).thenReturn(liveData)
        Mockito.`when`(viewModel.getCategoryId()).thenReturn(liveData)
        Mockito.`when`(viewModel.getBill()).thenReturn(billLiveData)

        fragment.viewModelFactory = ViewModelUtil.createFor(viewModel)
        activityRule.activity.setFragment(fragment)

        EspressoAnimationTestUtil.disableProgressBarAnimations(activityRule)
    }

    @Test
    fun errorIsShownWhenBillNameIsEmpty() {
        //When button is clicked
        onView(withId(R.id.btn_bottom_sheet_dialog_fragment)).perform(click())

        onView(withId(R.id.input_layout_bill_name))
                .check(matches(withErrorInInputLayout(activityRule.activity.resources.getString(R.string.error_no_name))))
    }

    @Test
    fun errorIsShownWhenBillNumberIsEmpty() {

        onView(withId(R.id.et_bill_name))
                .perform(typeText("Zuku"), closeSoftKeyboard())

        //When button is clicked
        onView(withId(R.id.btn_bottom_sheet_dialog_fragment)).perform(click())

        //Verify that the error is shown.
        onView(withId(R.id.input_layout_bill_number))
                .check(matches(withErrorInInputLayout(
                        activityRule.activity.resources.getString(R.string.error_no_pay_bill_number))
                ))
    }

    @Test
    fun errorIsShownWhenAccountNumberIsEmpty() {
        onView(withId(R.id.et_bill_name))
                .perform(typeText("Zuku"), pressImeActionButton())

        onView(withId(R.id.et_bill_number))
                .perform(typeText("ZUKU1234"), closeSoftKeyboard())

        //When button is clicked
        onView(withId(R.id.btn_bottom_sheet_dialog_fragment)).perform(click())

        //Verify that the error is shown.
        onView(withId(R.id.input_layout_account_number))
                .check(matches(withErrorInInputLayout(
                        activityRule.activity.resources.getString(R.string.error_no_account_number))
                ))
    }

    @Test
    fun editTextPayBillNameHasCorrectText() {

        onView(withId(R.id.et_bill_name))
                .perform(typeText("Zuku"), pressImeActionButton())

        onView(allOf(withId(R.id.et_bill_name))).check(matches(withText("Zuku")))

    }

    @Test
    fun editTextAccountNumberHasCorrectText() {

        onView(withId(R.id.et_account_number))
                .perform(typeText("ZUKU1234"), closeSoftKeyboard())

        onView(allOf(withId(R.id.et_account_number))).check(matches(withText("ZUKU1234")))

    }

    @Test
    fun editTextBillNumberHasCorrectText() {

        onView(withId(R.id.et_bill_number))
                .perform(typeText("320320"), closeSoftKeyboard())

        onView(allOf(withId(R.id.et_bill_number))).check(matches(withText("320320")))

    }
}