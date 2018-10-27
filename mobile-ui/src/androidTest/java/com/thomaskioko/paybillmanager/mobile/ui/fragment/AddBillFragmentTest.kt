package com.thomaskioko.paybillmanager.mobile.ui.fragment

import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.SimpleFragmentActivity
import com.thomaskioko.paybillmanager.mobile.ui.NavigationController
import com.thomaskioko.paybillmanager.mobile.util.ToolbarViewMarcher
import com.thomaskioko.paybillmanager.mobile.util.ViewModelUtil
import com.thomaskioko.paybillmanager.presentation.model.CategoryView
import com.thomaskioko.paybillmanager.presentation.state.Resource
import com.thomaskioko.paybillmanager.presentation.viewmodel.category.CreateCategoryViewModel
import com.thomaskioko.paybillmanager.presentation.viewmodel.category.GetCategoriesViewModel
import com.thomaskioko.xapotest.util.EspressoTestUtil
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class AddBillFragmentTest {


    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SimpleFragmentActivity::class.java, true, true)

    private lateinit var getCategoriesViewModel: GetCategoriesViewModel

    private val liveData = MutableLiveData<Resource<List<CategoryView>>>()

    private lateinit var navigationController: NavigationController

    @Before
    fun init() {
        val fragment = AddBillFragment()

        navigationController = Mockito.mock(NavigationController::class.java)

        getCategoriesViewModel = Mockito.mock(GetCategoriesViewModel::class.java)
        Mockito.`when`(getCategoriesViewModel.getCategories()).thenReturn(liveData)

        fragment.viewModelFactory = ViewModelUtil.createFor(getCategoriesViewModel)
        activityRule.activity.setFragment(fragment)

        EspressoTestUtil.disableProgressBarAnimations(activityRule)

    }

    @Test
    fun bill_amount_entered() {

        onView(withId(R.id.et_amount))
                .perform(ViewActions.typeText("2300"), closeSoftKeyboard())

    }


}
