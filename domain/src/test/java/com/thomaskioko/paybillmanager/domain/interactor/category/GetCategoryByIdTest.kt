package com.thomaskioko.paybillmanager.domain.interactor.category

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.domain.category.GetCategoryById
import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.factory.TestDataFactory
import com.thomaskioko.paybillmanager.domain.model.Category
import com.thomaskioko.paybillmanager.domain.repository.CategoryRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetCategoryByIdTest {

    private lateinit var getCategoryById: GetCategoryById
    @Mock
    lateinit var categoryRepository: CategoryRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getCategoryById = GetCategoryById(categoryRepository, postExecutionThread)
    }

    @Test
    fun getCategoryCompletes() {
        stubGetBillsRepository(Observable.just(TestDataFactory.makeCategory()))

        val testObservable = getCategoryById.buildUseCaseObservable(
                GetCategoryById.Params.forCategory(TestDataFactory.randomUuid())
        ).test()
        testObservable.assertComplete()
    }

    @Test
    fun getCategoryReturnsData() {
        val category = TestDataFactory.makeCategory()

        //Stub the repository completes
        stubGetBillsRepository(Observable.just(category))

        val testObserver = getCategoryById.buildUseCaseObservable(
                GetCategoryById.Params.forCategory(TestDataFactory.randomUuid())
        ).test()

        //Verify that the data returned is what is expected
        testObserver.assertValue(category)
    }


    private fun stubGetBillsRepository(observable: Observable<Category>) {
        whenever(categoryRepository.getCategoryById(any())).thenReturn(observable)
    }
}