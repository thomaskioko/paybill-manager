package com.thomaskioko.paybillmanager.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import com.thomaskioko.paybillmanager.domain.bills.CreateBill
import com.thomaskioko.paybillmanager.domain.bills.UpdateBill
import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.presentation.factory.BillsFactory
import com.thomaskioko.paybillmanager.presentation.factory.DataFactory
import com.thomaskioko.paybillmanager.presentation.mapper.BillViewMapper
import com.thomaskioko.paybillmanager.presentation.model.BillView
import com.thomaskioko.paybillmanager.presentation.state.ResourceState
import io.reactivex.observers.DisposableCompletableObserver
import junit.framework.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor


@RunWith(JUnit4::class)
class CreateBillsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Captor
    val captor = argumentCaptor<DisposableCompletableObserver>()

    private var mapper = mock<BillViewMapper>()
    private var createBill = mock<CreateBill>()
    private var updateBill = mock<UpdateBill>()
    private var createBillsViewModel = CreateBillsViewModel(createBill, updateBill)

    @Test
    fun createBillExecutesUseCase() {
        val bill = BillsFactory.makeStaticBill()

        //invoke create bill
        createBillsViewModel.createBill(bill)

        //Use captor to capture the response when execute is called
        verify(createBill).execute(captor.capture(), eq(CreateBill.Params.forBill(bill)))
    }

    @Test
    fun createBillReturnsSuccess() {
        val billObject = BillsFactory.makeStaticBill()
        val bill = BillsFactory.makeBill()
        val billView = BillsFactory.makeBillView()
        stubBillMapperMapToView(billView, bill)

        //invoke create bill
        createBillsViewModel.createBill(BillsFactory.makeStaticBill())

        //Use captor to capture the response when execute is called
        verify(createBill).execute(captor.capture(), eq(CreateBill.Params.forBill(billObject)))

        //Pass data to onNext callback
        captor.firstValue.onComplete()

        //Verify that resource type returned is of type success
        TestCase.assertEquals(ResourceState.SUCCESS,
                createBillsViewModel.getBill().value?.status)
    }

    @Test
    fun createBillReturnsError() {

        val bill = BillsFactory.makeStaticBill()

        //invoke create bill
        createBillsViewModel.createBill(bill)

        //Use captor to capture the response when execute is called
        verify(createBill).execute(captor.capture(), eq(CreateBill.Params.forBill(bill)))


        //Pass Exception to onError callback
        captor.firstValue.onError(RuntimeException())

        //Verify that resource type returned is of type error
        TestCase.assertEquals(ResourceState.ERROR, createBillsViewModel.getBill().value?.status)
    }

    @Test
    fun createBillMessageForError() {
        val errorMessage = DataFactory.randomString()

        val bill = BillsFactory.makeStaticBill()

        //invoke create bill
        createBillsViewModel.createBill(bill)

        //Use captor to capture the response when execute is called
        verify(createBill).execute(captor.capture(), eq(CreateBill.Params.forBill(bill)))


        //Pass error message to onError callback
        captor.firstValue.onError(RuntimeException(errorMessage))

        //Verify that the error message returned is what is expected
        TestCase.assertEquals(errorMessage, createBillsViewModel.getBill().value?.message)
    }

    private fun stubBillMapperMapToView(projectView: BillView, project: Bill) {
        whenever(mapper.mapToView(project)).thenReturn(projectView)
    }

}