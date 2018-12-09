package com.thomaskioko.paybillmanager.domain.interactor.category

import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.domain.category.GetCategories
import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.factory.TestDataFactory
import com.thomaskioko.paybillmanager.domain.model.Category
import com.thomaskioko.paybillmanager.domain.repository.CategoryRepository
import io.reactivex.Observable
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

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getCategories = GetCategories(categoryRepository, postExecutionThread)
    }

    @Test
    fun getCategoriesCompletes() {
        stubGetCategoriesRepository(Observable.just(TestDataFactory.makeCategoriesList(3)))

        val testObservable = getCategories.buildUseCaseObservable().test()
        testObservable.assertComplete()
    }

    @Test
    fun getCategoriesReturnsData() {
        val categories = TestDataFactory.makeCategoriesList(4)

        //Stub the repository completes
        stubGetCategoriesRepository(Observable.just(categories))

        val testObserver = getCategories.buildUseCaseObservable().test()

        //Verify that the data returned is what is expected
        testObserver.assertValue(categories)
    }


    private fun stubGetCategoriesRepository(observable: Observable<List<Category>>) {
        whenever(categoryRepository.getCategories()).thenReturn(observable)
    }
}