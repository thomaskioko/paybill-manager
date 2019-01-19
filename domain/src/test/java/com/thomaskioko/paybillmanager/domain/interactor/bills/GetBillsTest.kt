package com.thomaskioko.paybillmanager.domain.interactor.bills

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

class GetBillsTest {

    private lateinit var getBills: GetBills
    @Mock
    lateinit var billsRepository: BillsRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread
    @Mock
    private lateinit var threadExecutor: ThreadExecutor

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getBills = GetBills(billsRepository, threadExecutor, postExecutionThread)
    }

    @Test
    fun getBillsCompletes() {
        stubGetBillsRepository(Flowable.just(TestDataFactory.makeProjectList(3)))

        val testObservable = getBills.buildUseCaseObservable().test()
        testObservable.assertComplete()
    }

    @Test
    fun getBillsReturnsData() {
        val bills = TestDataFactory.makeProjectList(4)

        //Stub the repository completes
        stubGetBillsRepository(Flowable.just(bills))

        val testObserver = getBills.buildUseCaseObservable().test()

        //Verify that the data returned is what is expected
        testObserver.assertValue(bills)
    }


    private fun stubGetBillsRepository(observable: Flowable<List<Bill>>) {
        whenever(billsRepository.getBills()).thenReturn(observable)
    }
}