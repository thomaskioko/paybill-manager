package com.thomaskioko.paybillmanager.presentation.viewmodel.billcategory

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.thomaskioko.paybillmanager.domain.interactor.billcategory.CreateBillsCategory
import com.thomaskioko.paybillmanager.presentation.factory.BillCategoryFactory
import com.thomaskioko.paybillmanager.presentation.state.ResourceState
import io.reactivex.observers.DisposableCompletableObserver
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor

@RunWith(JUnit4::class)
class CreateGetBillCategoryViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Captor
    val captor = argumentCaptor<DisposableCompletableObserver>()

    private var createBillsCategory = mock<CreateBillsCategory>()
    private val viewModel = CreateBillCategoryViewModel(createBillsCategory)

    @Test
    fun createBillCategoryExecutesUseCase() {

        val billCategory = BillCategoryFactory.makeBillCategory()

        //Invoke create bill
        viewModel.createBillCategory(billCategory)

        //Use captor to capture the response when execute method is called
        verify(createBillsCategory).execute(
                captor.capture(),
                eq(CreateBillsCategory.Params.forBillCategory(billCategory))
        )

    }

    @Test
    fun createBillCategoryReturnSuccess() {

        val billCategory = BillCategoryFactory.makeBillCategory()

        //Invoke create bill
        viewModel.createBillCategory(billCategory)

        //Use captor to capture the response when execute method is called
        verify(createBillsCategory).execute(
                captor.capture(),
                eq(CreateBillsCategory.Params.forBillCategory(billCategory))
        )

        captor.firstValue.onComplete()

        assertEquals(ResourceState.SUCCESS, viewModel.getBillCategory().value?.status)

    }

    @Test
    fun createBillCategoryReturnError() {

        val billCategory = BillCategoryFactory.makeBillCategory()

        //Invoke create bill
        viewModel.createBillCategory(billCategory)

        //Use captor to capture the response when execute method is called
        verify(createBillsCategory).execute(
                captor.capture(),
                eq(CreateBillsCategory.Params.forBillCategory(billCategory))
        )

        //Pass Exception to onError callback
        captor.firstValue.onError(RuntimeException())

        assertEquals(ResourceState.ERROR, viewModel.getBillCategory().value?.status)

    }
}
