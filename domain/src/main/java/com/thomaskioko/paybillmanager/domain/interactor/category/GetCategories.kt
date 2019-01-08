package com.thomaskioko.paybillmanager.domain.interactor.category

import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.executor.ThreadExecutor
import com.thomaskioko.paybillmanager.domain.model.Category
import com.thomaskioko.paybillmanager.domain.repository.CategoryRepository
import com.thomaskioko.paybillmanager.domain.usecase.FlowableUseCase
import io.reactivex.Flowable
import javax.inject.Inject

open class GetCategories @Inject constructor(
        private val categoryRepository: CategoryRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
) : FlowableUseCase<List<Category>, Nothing?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Nothing?): Flowable<List<Category>> {
        return categoryRepository.getCategories()
    }

}