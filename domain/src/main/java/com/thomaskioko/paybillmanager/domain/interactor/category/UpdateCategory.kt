package com.thomaskioko.paybillmanager.domain.interactor.category

import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.usecase.CompletableUseCase
import com.thomaskioko.paybillmanager.domain.model.Category
import com.thomaskioko.paybillmanager.domain.repository.CategoryRepository
import io.reactivex.Completable
import javax.inject.Inject

open class UpdateCategory @Inject constructor(
        private val categoryRepository: CategoryRepository,
        postExecutionThread: PostExecutionThread
) : CompletableUseCase<UpdateCategory.Params>(postExecutionThread) {

    public override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return categoryRepository.updateCategory(params.category)
    }

    data class Params constructor(val category: Category) {
        companion object {
            fun forCategory(category: Category): Params {
                return Params(category)
            }
        }
    }

}