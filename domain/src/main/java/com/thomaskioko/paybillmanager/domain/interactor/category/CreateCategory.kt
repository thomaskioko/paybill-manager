package com.thomaskioko.paybillmanager.domain.interactor.category

import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.executor.ThreadExecutor
import com.thomaskioko.paybillmanager.domain.model.Category
import com.thomaskioko.paybillmanager.domain.repository.CategoryRepository
import com.thomaskioko.paybillmanager.domain.usecase.CompletableUseCase
import io.reactivex.Completable
import javax.inject.Inject

open class CreateCategory @Inject constructor(
        private val categoryRepository: CategoryRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
) : CompletableUseCase<CreateCategory.Params>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return categoryRepository.createCategory(params.category)
    }

    data class Params constructor(val category: Category) {
        companion object {
            fun forCategory(category: Category): Params {
                return Params(category)
            }
        }
    }
}