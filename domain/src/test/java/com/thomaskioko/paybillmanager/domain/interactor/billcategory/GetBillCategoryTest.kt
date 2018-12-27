package com.thomaskioko.paybillmanager.domain.interactor.billcategory

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.factory.TestDataFactory
import com.thomaskioko.paybillmanager.domain.model.BillCategory
import com.thomaskioko.paybillmanager.domain.repository.BillCategoryRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetBillCategoryTest {

    private lateinit var getBillCategory: GetBillCategory

    @Mock
    lateinit var repository: BillCategoryRepository

    @Mock
    lateinit var postExecutionThread: PostExecutionThread


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getBillCategory = GetBillCategory(repository, postExecutionThread)
    }

    @Test
    fun getBillCategoryCompletes(){
        val billCategory = TestDataFactory.makeBillCategory()

        stubGetBillsRepository(Observable.just(billCategory))

        val testObservable = getBillCategory.buildUseCaseObservable(
                GetBillCategory.Params.forBillCategory(TestDataFactory.randomUuid(), TestDataFactory.randomUuid())
        ).test()

        //Verify that the observable completes
        testObservable.assertComplete()

    }


    @Test
    fun getBillCategoryReturnsData(){
        val billCategory = TestDataFactory.makeBillCategory()

        stubGetBillsRepository(Observable.just(billCategory))

        val testObservable = getBillCategory.buildUseCaseObservable(
                GetBillCategory.Params.forBillCategory(TestDataFactory.randomUuid(), TestDataFactory.randomUuid())
        ).test()

        //Verify that thte observable return data
        testObservable.assertValue(billCategory)

    }

    private fun stubGetBillsRepository(observable: Observable<BillCategory>){
        whenever(repository.getBillCategory(any(), any())).thenReturn(observable)
    }
}