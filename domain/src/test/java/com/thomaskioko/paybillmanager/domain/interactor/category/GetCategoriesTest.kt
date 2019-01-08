package com.thomaskioko.paybillmanager.domain.interactor.category

import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.executor.ThreadExecutor
import com.thomaskioko.paybillmanager.domain.factory.TestDataFactory
import com.thomaskioko.paybillmanager.domain.model.Category
import com.thomaskioko.paybillmanager.domain.repository.CategoryRepository
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetCategoriesTest {

    private lateinit var getCategories: GetCategories
    @Mock
    lateinit var categoryRepository: CategoryRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread
    @Mock
    private lateinit var threadExecutor: ThreadExecutor

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getCategories = GetCategories(categoryRepository, threadExecutor, postExecutionThread)
    }

    @Test
    fun getCategoriesCompletes() {
        stubGetCategoriesRepository(Flowable.just(TestDataFactory.makeCategoriesList(3)))

        val testObservable = getCategories.buildUseCaseObservable().test()
        testObservable.assertComplete()
    }

    @Test
    fun getCategoriesReturnsData() {
        val categories = TestDataFactory.makeCategoriesList(4)

        //Stub the repository completes
        stubGetCategoriesRepository(Flowable.just(categories))

        val testObserver = getCategories.buildUseCaseObservable().test()

        //Verify that the data returned is what is expected
        testObserver.assertValue(categories)
    }


    private fun stubGetCategoriesRepository(observable: Flowable<List<Category>>) {
        whenever(categoryRepository.getCategories()).thenReturn(observable)
    }
}