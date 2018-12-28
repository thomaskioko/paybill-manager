package com.thomaskioko.paybillmanager.domain.interactor.billcategory

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.factory.TestDataFactory
import com.thomaskioko.paybillmanager.domain.model.Category
import com.thomaskioko.paybillmanager.domain.repository.BillCategoryRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetCategoryByBillIdTest {

    private lateinit var getBillCategory: GetCategoryByBillId

    @Mock
    lateinit var repository: BillCategoryRepository

    @Mock
    lateinit var postExecutionThread: PostExecutionThread


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getBillCategory = GetCategoryByBillId(repository, postExecutionThread)
    }

    @Test
    fun getCategoryByBillIdCompletes() {
        val category = TestDataFactory.makeCategory()

        stubGetCategoryByBillIdRepository(Observable.just(category))

        val testObservable = getBillCategory.buildUseCaseObservable(
                GetCategoryByBillId.Params.forCategoryByBillId(TestDataFactory.randomUuid())
        ).test()

        //Verify that the observable completes
        testObservable.assertComplete()

    }


    @Test
    fun getCategoryByBillIdReturnsData() {
        val category = TestDataFactory.makeCategory()

        stubGetCategoryByBillIdRepository(Observable.just(category))

        val testObservable = getBillCategory.buildUseCaseObservable(
                GetCategoryByBillId.Params.forCategoryByBillId(TestDataFactory.randomUuid())
        ).test()

        //Verify that the observable return data
        testObservable.assertValue(category)

    }

    private fun stubGetCategoryByBillIdRepository(observable: Observable<Category>) {
        whenever(repository.getCategoryByBillId(any())).thenReturn(observable)
    }
}