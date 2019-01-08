package com.thomaskioko.paybillmanager.domain.interactor.billcategory

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.executor.ThreadExecutor
import com.thomaskioko.paybillmanager.domain.factory.TestDataFactory
import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.domain.repository.BillCategoryRepository
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetBillsByCategoryIdTest {

    private lateinit var getBillCategory: GetBillsByCategoryId

    @Mock
    lateinit var repository: BillCategoryRepository

    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Mock
    private lateinit var threadExecutor: ThreadExecutor


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getBillCategory = GetBillsByCategoryId(repository, threadExecutor, postExecutionThread)
    }

    @Test
    fun getBillsByCategoryIdCompletes() {
        val list = listOf(TestDataFactory.makeBill())

        stubGetBillsByCategoryIdRepository(Flowable.just(list))

        val testObservable = getBillCategory.buildUseCaseObservable(
                GetBillsByCategoryId.Params.forBillsByCategoryId(TestDataFactory.randomUuid())
        ).test()

        //Verify that the observable completes
        testObservable.assertComplete()

    }


    @Test
    fun getBillsByCategoryIdReturnsData() {
        val list = listOf(TestDataFactory.makeBill())

        stubGetBillsByCategoryIdRepository(Flowable.just(list))

        val testObservable = getBillCategory.buildUseCaseObservable(
                GetBillsByCategoryId.Params.forBillsByCategoryId(TestDataFactory.randomUuid())
        ).test()

        //Verify that the observable return data
        testObservable.assertValue(list)

    }

    private fun stubGetBillsByCategoryIdRepository(observable: Flowable<List<Bill>>) {
        whenever(repository.getBillsByCategoryId(any())).thenReturn(observable)
    }
}