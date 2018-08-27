package com.thomaskioko.paybillmanager.domain.interactor.bills

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.domain.bills.UpdateBill
import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.factory.BillsDataFactory
import com.thomaskioko.paybillmanager.domain.repository.BillsRepository
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class UpdateBillTest {

    private lateinit var updateBill: UpdateBill
    @Mock
    lateinit var billsRepository: BillsRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        updateBill = UpdateBill(billsRepository, postExecutionThread)
    }

    @Test
    fun updateBillCompletes(){
        stubCreateBillRepository(Completable.complete())

        val testObserver = updateBill.buildUseCaseCompletable(
                UpdateBill.Params.forBill(BillsDataFactory.makeBill())
        ).test()

        //Verify that the observable completes
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun updateBillThrowsException(){
        updateBill.buildUseCaseCompletable().test()
    }


    private fun stubCreateBillRepository(completable: Completable) {
        whenever(billsRepository.updateBill(any())).thenReturn(completable)
    }
}
