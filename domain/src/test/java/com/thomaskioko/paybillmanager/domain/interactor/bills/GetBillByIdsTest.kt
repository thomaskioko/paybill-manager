package com.thomaskioko.paybillmanager.domain.interactor.bills

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.executor.ThreadExecutor
import com.thomaskioko.paybillmanager.domain.factory.TestDataFactory
import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.domain.repository.BillsRepository
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetBillByIdsTest {

    private lateinit var getBillByIds: GetBillByIds
    @Mock
    lateinit var billsRepository: BillsRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread
    @Mock
    private lateinit var threadExecutor: ThreadExecutor

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getBillByIds = GetBillByIds(billsRepository, threadExecutor, postExecutionThread)
    }

    @Test
    fun getBillByIdsCompletes() {
        stubGetBillsRepository(Flowable.just(TestDataFactory.makeBill()))

        val testObservable = getBillByIds.buildUseCaseObservable(
                GetBillByIds.Params.forBillByIds(TestDataFactory.randomUuid(), TestDataFactory.randomUuid())
        ).test()
        testObservable.assertComplete()
    }

    @Test
    fun getBillByIdsReturnsData() {
        val bills = TestDataFactory.makeBill()

        //Stub the repository completes
        stubGetBillsRepository(Flowable.just(bills))

        val testObserver = getBillByIds.buildUseCaseObservable(
                GetBillByIds.Params.forBillByIds(TestDataFactory.randomUuid(), TestDataFactory.randomUuid())
        ).test()

        //Verify that the data returned is what is expected
        testObserver.assertValue(bills)
    }


    private fun stubGetBillsRepository(observable: Flowable<Bill>) {
        whenever(billsRepository.getBillByIds(any(), any())).thenReturn(observable)
    }
}