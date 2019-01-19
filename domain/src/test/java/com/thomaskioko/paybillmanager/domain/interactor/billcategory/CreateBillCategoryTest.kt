package com.thomaskioko.paybillmanager.domain.interactor.billcategory

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.executor.ThreadExecutor
import com.thomaskioko.paybillmanager.domain.factory.TestDataFactory
import com.thomaskioko.paybillmanager.domain.repository.BillCategoryRepository
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CreateBillCategoryTest {

    private lateinit var getBillCategory: CreateBillsCategory

    @Mock
    lateinit var repository: BillCategoryRepository

    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Mock
    private lateinit var threadExecutor: ThreadExecutor


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getBillCategory = CreateBillsCategory(repository, threadExecutor, postExecutionThread)
    }

    @Test
    fun getBillsByCategoryIdCompletes() {

        stubCreateBillCategoryRepository(Completable.complete())

        val testObservable = getBillCategory.buildUseCaseCompletable(
                CreateBillsCategory.Params.forBillCategory(TestDataFactory.makeBillCategory())
        ).test()

        //Verify that the observable completes
        testObservable.assertComplete()

    }

    private fun stubCreateBillCategoryRepository(completable: Completable) {
        whenever(repository.createBillCategory(any())).thenReturn(completable)
    }
}