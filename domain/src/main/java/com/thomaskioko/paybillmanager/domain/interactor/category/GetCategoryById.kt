package com.thomaskioko.paybillmanager.domain.interactor.category

import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.executor.ThreadExecutor
import com.thomaskioko.paybillmanager.domain.model.Category
import com.thomaskioko.paybillmanager.domain.repository.CategoryRepository
import com.thomaskioko.paybillmanager.domain.usecase.FlowableUseCase
import io.reactivex.Flowable
import javax.inject.Inject

open class GetCategoryById @Inject constructor(
        private val categoryRepository: CategoryRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
) : FlowableUseCase<Category, GetCategoryById.Params?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Params?): Flowable<Category> {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return categoryRepository.getCategoryById(params.categoryId)
    }


    data class Params constructor(val categoryId: String) {
        companion object {
            fun forCategory(categoryId: String): Params {
                return Params(categoryId)
            }
        }
    }

}