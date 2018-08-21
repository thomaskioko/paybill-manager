package com.thomaskioko.paybillmanager.domain.interactor

import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.domain.factory.BillsDataFactory
import com.thomaskioko.paybillmanager.domain.bills.GetBills
import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.domain.repository.BillsRepository
import io.reactivex.Observable
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

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getBills = GetBills(billsRepository, postExecutionThread)
    }

    @Test
    fun getBillsCompletes(){
        stubGetBillsRepository(Observable.just(BillsDataFactory.makeProjectList(3)))

        val testObservable = getBills.buildUseCaseObservable().test()
        testObservable.assertComplete()
    }


    private fun stubGetBillsRepository(observable: Observable<List<Bill>>){
        whenever(billsRepository.getBills()).thenReturn(observable)
    }
}