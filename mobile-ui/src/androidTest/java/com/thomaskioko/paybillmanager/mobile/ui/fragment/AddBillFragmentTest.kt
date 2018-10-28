package com.thomaskioko.paybillmanager.mobile.ui.fragment

import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.SimpleFragmentActivity
import com.thomaskioko.paybillmanager.mobile.ui.NavigationController
import com.thomaskioko.paybillmanager.mobile.util.ViewModelUtil
import com.thomaskioko.paybillmanager.presentation.model.CategoryView
import com.thomaskioko.paybillmanager.presentation.state.Resource
import com.thomaskioko.paybillmanager.presentation.viewmodel.category.GetCategoriesViewModel
import com.thomaskioko.paybillmanager.mobile.util.EspressoAnimationTestUtil
import com.thomaskioko.paybillmanager.presentation.viewmodel.CreateBillsViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class AddBillFragmentTest {


    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SimpleFragmentActivity::class.java, true, true)

    private lateinit var getCategoriesViewModel: GetCategoriesViewModel
    private lateinit var createBillsViewModel: CreateBillsViewModel
    private lateinit var navigationController: NavigationController

    private val stringLiveData = MutableLiveData<String>()
    private val liveData = MutableLiveData<Resource<List<CategoryView>>>()

    @Before
    fun init() {
        val fragment = AddBillFragment()

        navigationController = Mockito.mock(NavigationController::class.java)

        createBillsViewModel = Mockito.mock(CreateBillsViewModel::class.java)
        getCategoriesViewModel = Mockito.mock(GetCategoriesViewModel::class.java)
        Mockito.`when`(getCategoriesViewModel.getCategories()).thenReturn(liveData)
        Mockito.`when`(createBillsViewModel.getAmount()).thenReturn(stringLiveData)
        Mockito.`when`(createBillsViewModel.getCategoryId()).thenReturn(stringLiveData)

        fragment.viewModelFactory = ViewModelUtil.createFor(getCategoriesViewModel)
        fragment.viewModelFactory = ViewModelUtil.createFor(createBillsViewModel)
        activityRule.activity.setFragment(fragment)

        EspressoAnimationTestUtil.disableProgressBarAnimations(activityRule)

    }


    fun bill_amount_entered() {

        onView(withId(R.id.et_amount))
                .perform(ViewActions.typeText("2300"), closeSoftKeyboard())

    }


}
