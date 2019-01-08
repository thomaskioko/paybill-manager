package com.thomaskioko.paybillmanager.presentation.viewmodel.biil

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import com.thomaskioko.paybillmanager.domain.interactor.bills.GetBillByBillId
import com.thomaskioko.paybillmanager.domain.interactor.bills.GetBillByIds
import com.thomaskioko.paybillmanager.domain.interactor.bills.GetBills
import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.presentation.factory.BillsFactory
import com.thomaskioko.paybillmanager.presentation.factory.DataFactory
import com.thomaskioko.paybillmanager.presentation.mapper.BillViewMapper
import com.thomaskioko.paybillmanager.presentation.model.BillView
import com.thomaskioko.paybillmanager.presentation.state.ResourceState
import com.thomaskioko.paybillmanager.presentation.viewmodel.bill.GetBillsViewModel
import io.reactivex.subscribers.DisposableSubscriber
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor


@RunWith(JUnit4::class)
class GetBillsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Captor
    val captorList = argumentCaptor<DisposableSubscriber<List<Bill>>>()

    @Captor
    val captor = argumentCaptor<DisposableSubscriber<Bill>>()

    private var getBills = mock<GetBills>()
    private var getBillByBillId = mock<GetBillByBillId>()
    private var getBillById = mock<GetBillByIds>()
    private var mapper = mock<BillViewMapper>()
    private var billViewModel = GetBillsViewModel(getBillByBillId, getBillById, getBills, mapper)


    @Test
    fun fetchBillsExecutesUseCase() {
        billViewModel.fetchBills()

        //verify that fetch bills use case is called once
        verify(getBills, times(1)).execute(any(), eq(null))
    }

    @Test
    fun fetchBillExecutesUseCase() {
        billViewModel.fetchBillByBillId("24")

        //verify that fetch bill by id use case is called once
        verify(getBillByBillId, times(1)).execute(any(), eq(GetBillByBillId.Params.forBill("24")))
    }

    @Test
    fun fetchBillByIdExecutesUseCase() {
        billViewModel.fetchBillById("24", "34")

        //verify that fetch bill by id use case is called once
        verify(getBillById, times(1)).execute(
                any(),
                eq(GetBillByIds.Params.forBillByIds("24", "34")))
    }


    @Test
    fun fetchBillsReturnsSuccess() {
        val bills = BillsFactory.makeBillList(2)
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
    fun fetchBillByBillIdReturnsSuccess() {
        val bill = BillsFactory.makeBill()
        val billView = BillsFactory.makeBillView()
        stubBillMapperMapToView(billView, bill)

        //invoke fetch fetchBillByBillId
        billViewModel.fetchBillByBillId("24")

        //Use captor to capture the response when execute is called
        verify(getBillByBillId).execute(captor.capture(), eq(GetBillByBillId.Params.forBill("24")))

        //Pass data to onNext callback
        captor.firstValue.onNext(bill)

        //Verify that resource type returned is of type success
        TestCase.assertEquals(ResourceState.SUCCESS, billViewModel.getBill().value?.status)
    }

    @Test
    fun fetchBillByIdsReturnsSuccess() {
        val bill = BillsFactory.makeBill()
        val billView = BillsFactory.makeBillView()
        stubBillMapperMapToView(billView, bill)

        //invoke fetch fetchBillByBillId
        billViewModel.fetchBillById("24", "45")

        //Use captor to capture the response when execute is called
        verify(getBillById).execute(
                captor.capture(),
                eq(GetBillByIds.Params.forBillByIds("24", "45"))
        )

        //Pass data to onNext callback
        captor.firstValue.onNext(bill)

        //Verify that resource type returned is of type success
        TestCase.assertEquals(ResourceState.SUCCESS, billViewModel.getBill().value?.status)
    }

    @Test
    fun fetchBillsReturnsData() {
        val bills = BillsFactory.makeBillList(2)
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
        TestCase.assertEquals(billViews, billViewModel.getBills().value?.data)
    }

    @Test
    fun fetchBillByBillIdReturnsData() {
        val bill = BillsFactory.makeBill()
        val billView = BillsFactory.makeBillView()
        stubBillMapperMapToView(billView, bill)

        //invoke fetch fetchBillByBillId
        billViewModel.fetchBillByBillId("24")

        //Use captor to capture the response when execute is called
        verify(getBillByBillId).execute(captor.capture(), eq(GetBillByBillId.Params.forBill("24")))

        //Pass data to onNext callback
        captor.firstValue.onNext(bill)

        //Verify that the data returned is what is returned
        TestCase.assertEquals(billView, billViewModel.getBill().value?.data)
    }

    @Test
    fun fetchBillByIdReturnsData() {
        val bill = BillsFactory.makeBill()
        val billView = BillsFactory.makeBillView()
        stubBillMapperMapToView(billView, bill)

        //invoke fetch fetchBillByBillId
        billViewModel.fetchBillById("24", "45")

        //Use captor to capture the response when execute is called
        verify(getBillById).execute(
                captor.capture(),
                eq(GetBillByIds.Params.forBillByIds("24", "45"))
        )

        //Pass data to onNext callback
        captor.firstValue.onNext(bill)

        //Verify that the data returned is what is returned
        TestCase.assertEquals(billView, billViewModel.getBill().value?.data)
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
        TestCase.assertEquals(ResourceState.ERROR, billViewModel.getBills().value?.status)
    }

    @Test
    fun fetchBillByBillIdReturnsError() {
        //invoke fetch bill by id
        billViewModel.fetchBillByBillId("24")

        //Use captor to capture the response when execute is called
        verify(getBillByBillId).execute(captor.capture(), eq(GetBillByBillId.Params.forBill("24")))


        //Pass Exception to onError callback
        captor.firstValue.onError(RuntimeException())

        //Verify that resource type returned is of type error
        TestCase.assertEquals(ResourceState.ERROR, billViewModel.getBill().value?.status)
    }

    @Test
    fun fetchBillByIdReturnsError() {
        //invoke fetch bill by id
        billViewModel.fetchBillById("24", "34")

        //Use captor to capture the response when execute is called
        verify(getBillById).execute(
                captor.capture(),
                eq(GetBillByIds.Params.forBillByIds("24", "34"))
        )

        //Pass Exception to onError callback
        captor.firstValue.onError(RuntimeException())

        //Verify that resource type returned is of type error
        TestCase.assertEquals(ResourceState.ERROR, billViewModel.getBill().value?.status)
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
        TestCase.assertEquals(errorMessage, billViewModel.getBills().value?.message)
    }

    @Test
    fun fetchBillByBillIdReturnsMessageForError() {
        val errorMessage = DataFactory.randomString()

        //invoke fetch bill by id
        billViewModel.fetchBillByBillId("24")

        //Use captor to capture the response when execute is called
        verify(getBillByBillId).execute(captor.capture(), eq(GetBillByBillId.Params.forBill("24")))


        //Pass error message to onError callback
        captor.firstValue.onError(RuntimeException(errorMessage))

        //Verify that the error message returned is what is expected
        TestCase.assertEquals(errorMessage, billViewModel.getBill().value?.message)
    }

    @Test
    fun fetchBillByIdReturnsMessageForError() {
        val errorMessage = "Ooops! Something went wrong"

        //invoke fetch bill by id
        billViewModel.fetchBillById("24", "34")

        //Use captor to capture the response when execute is called
        verify(getBillById).execute(
                captor.capture(),
                eq(GetBillByIds.Params.forBillByIds("24", "34"))
        )


        //Pass error message to onError callback
        captor.firstValue.onError(RuntimeException(errorMessage))

        //Verify that the error message returned is what is expected
        assertEquals(errorMessage, billViewModel.getBill().value?.message)
    }

    private fun stubBillMapperMapToView(projectView: BillView, project: Bill) {
        whenever(mapper.mapToView(project)).thenReturn(projectView)
    }


}