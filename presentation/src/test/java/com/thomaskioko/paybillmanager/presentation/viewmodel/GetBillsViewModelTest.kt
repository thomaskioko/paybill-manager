package com.thomaskioko.paybillmanager.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import com.thomaskioko.paybillmanager.domain.bills.GetBillById
import com.thomaskioko.paybillmanager.domain.bills.GetBills
import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.presentation.factory.BillsFactory
import com.thomaskioko.paybillmanager.presentation.factory.DataFactory
import com.thomaskioko.paybillmanager.presentation.mapper.BillViewMapper
import com.thomaskioko.paybillmanager.presentation.model.BillView
import com.thomaskioko.paybillmanager.presentation.state.ResourceState
import io.reactivex.observers.DisposableObserver
import junit.framework.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor


@RunWith(JUnit4::class)
class GetBillsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var getBills = mock<GetBills>()
    private var getBillById = mock<GetBillById>()
    private var mapper = mock<BillViewMapper>()
    private var billViewModel = GetBillsViewModel(getBillById, getBills, mapper)


    @Captor
    val captorList = argumentCaptor<DisposableObserver<List<Bill>>>()


    @Test
    fun fetchBillsExecutesUseCase(){
        billViewModel.fetchBills()

        //verify that fetch bills use case is called once
        verify(getBills, times(1)).execute(any(), eq(null))
    }

    @Test
    fun fetchBillsReturnsSuccess() {
        val bills = BillsFactory.makeBillEntityList(2)
        val billViews = BillsFactory.makeBillViewList(2)
        stubBillMapperMapToView(billViews[0], bills[0])
        stubBillMapperMapToView(billViews[1], bills[1])

        //invoke fetch fetchBills
        billViewModel.fetchBills()

        //Use captor to capture the response when execute is called
        verify(getBills).execute(captorList.capture(), eq(null))

        //Pass data to onNext callback
        captorList.firstValue.onNext(bills)

        //Verify that resource type returned is of type success
        TestCase.assertEquals(ResourceState.SUCCESS,
                billViewModel.getBills().value?.status)
    }

    @Test
    fun fetchBillsReturnsData() {
        val bills = BillsFactory.makeBillEntityList(2)
        val billViews = BillsFactory.makeBillViewList(2)
        stubBillMapperMapToView(billViews[0], bills[0])
        stubBillMapperMapToView(billViews[1], bills[1])

        //invoke fetch fetchBills
        billViewModel.fetchBills()

        //Use captor to capture the response when execute is called
        verify(getBills).execute(captorList.capture(), eq(null))

        //Pass data to onNext callback
        captorList.firstValue.onNext(bills)

        //Verify that the data returned is what is returned
        TestCase.assertEquals(billViews,
                billViewModel.getBills().value?.data)
    }

    @Test
    fun fetchBillsReturnsError() {
        //invoke fetch bills
        billViewModel.fetchBills()

        //Use captor to capture the response when execute is called
        verify(getBills).execute(captorList.capture(), eq(null))

        //Pass Exception to onError callback
        captorList.firstValue.onError(RuntimeException())

        //Verify that resource type returned is of type error
        TestCase.assertEquals(ResourceState.ERROR,
                billViewModel.getBills().value?.status)
    }

    @Test
    fun fetchBillsReturnsMessageForError() {
        val errorMessage = DataFactory.randomString()

        //invoke fetch bills
        billViewModel.fetchBills()

        verify(getBills).execute(captorList.capture(), eq(null))

        //Pass error message to onError callback
        captorList.firstValue.onError(RuntimeException(errorMessage))

        //Verify that the error message returned is what is expected
        TestCase.assertEquals(errorMessage,
                billViewModel.getBills().value?.message)
    }

    private fun stubBillMapperMapToView(projectView: BillView,
                                        project: Bill) {
        whenever(mapper.mapToView(project))
                .thenReturn(projectView)
    }



}