package com.thomaskioko.paybillmanager.domain.interactor.category

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.executor.ThreadExecutor
import com.thomaskioko.paybillmanager.domain.factory.TestDataFactory
import com.thomaskioko.paybillmanager.domain.repository.CategoryRepository
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class UpdateCategoryTest {

    private lateinit var updateCategory: UpdateCategory
    @Mock
    lateinit var categoryRepository: CategoryRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread
    @Mock
    private lateinit var threadExecutor: ThreadExecutor

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        updateCategory = UpdateCategory(categoryRepository, threadExecutor, postExecutionThread)
    }

    @Test
    fun updateCategoryCompletes() {
        stubUpdateCategoryRepository(Completable.complete())

        val testObserver = updateCategory.buildUseCaseCompletable(
                UpdateCategory.Params.forCategory(TestDataFactory.makeCategory())
        ).test()

        //Verify that the observable completes
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun updateCategoryThrowsException() {
        updateCategory.buildUseCaseCompletable().test()
    }


    private fun stubUpdateCategoryRepository(completable: Completable) {
        whenever(categoryRepository.updateCategory(any())).thenReturn(completable)
    }
}
