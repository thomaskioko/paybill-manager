package com.thomaskioko.paybillmanager.domain.interactor.bills

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.domain.bills.CreateBill
import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.factory.TestDataFactory
import com.thomaskioko.paybillmanager.domain.repository.BillsRepository
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CreateBillTest {

    private lateinit var createBill: CreateBill
    @Mock
    lateinit var billsRepository: BillsRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        createBill = CreateBill(billsRepository, postExecutionThread)
    }

    @Test
    fun createBillCompletes(){
        stubCreateBillRepository(Completable.complete())

        val testObserver = createBill.buildUseCaseCompletable(
                CreateBill.Params.forBill(TestDataFactory.makeBill())
        ).test()

        //Verify that the observable completes
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun createBillThrowsException(){
        createBill.buildUseCaseCompletable().test()
    }


    private fun stubCreateBillRepository(completable: Completable) {
        whenever(billsRepository.createBill(any())).thenReturn(completable)
    }
}
