package com.thomaskioko.paybillmanager.domain.interactor

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.domain.bills.GetBillById
import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.factory.BillsDataFactory
import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.domain.repository.BillsRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetBillByIdTest {

    private lateinit var getBillById: GetBillById
    @Mock
    lateinit var billsRepository: BillsRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getBillById = GetBillById(billsRepository, postExecutionThread)
    }

    @Test
    fun getBillCompletes() {
        stubGetBillsRepository(Observable.just(BillsDataFactory.makeBill()))

        val testObservable = getBillById.buildUseCaseObservable(
                GetBillById.Params.forBill(BillsDataFactory.randomUuid())
        ).test()
        testObservable.assertComplete()
    }

    @Test
    fun getBillReturnsData() {
        val bills = BillsDataFactory.makeBill()

        //Stub the repository completes
        stubGetBillsRepository(Observable.just(bills))

        val testObserver = getBillById.buildUseCaseObservable(
                GetBillById.Params.forBill(BillsDataFactory.randomUuid())
        ).test()

        //Verify that the data returned is what is expected
        testObserver.assertValue(bills)
    }


    private fun stubGetBillsRepository(observable: Observable<Bill>) {
        whenever(billsRepository.getBillById(any())).thenReturn(observable)
    }
}