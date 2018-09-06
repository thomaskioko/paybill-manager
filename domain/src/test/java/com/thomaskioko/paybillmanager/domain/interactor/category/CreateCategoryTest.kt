package com.thomaskioko.paybillmanager.domain.interactor.category

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.domain.category.CreateCategory
import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.factory.TestDataFactory
import com.thomaskioko.paybillmanager.domain.repository.CategoryRepository
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CreateCategoryTest {

    private lateinit var createCategory: CreateCategory
    @Mock
    lateinit var categoryRepository: CategoryRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        createCategory = CreateCategory(categoryRepository, postExecutionThread)
    }

    @Test
    fun createCategoryCompletes(){
        stubCreateCategoryRepository(Completable.complete())

        val testObserver = createCategory.buildUseCaseCompletable(
                CreateCategory.Params.forCategory(TestDataFactory.makeCategory())
        ).test()

        //Verify that the observable completes
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun createCategoryThrowsException(){
        createCategory.buildUseCaseCompletable().test()
    }


    private fun stubCreateCategoryRepository(completable: Completable) {
        whenever(categoryRepository.createCategory(any())).thenReturn(completable)
    }
}
