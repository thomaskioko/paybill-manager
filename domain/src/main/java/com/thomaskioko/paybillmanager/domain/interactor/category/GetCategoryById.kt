package com.thomaskioko.paybillmanager.domain.interactor.category

import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.usecase.ObservableUseCase
import com.thomaskioko.paybillmanager.domain.model.Category
import com.thomaskioko.paybillmanager.domain.repository.CategoryRepository
import io.reactivex.Observable
import javax.inject.Inject

open class GetCategoryById @Inject constructor(
        private val categoryRepository: CategoryRepository,
        postExecutionThread: PostExecutionThread
) : ObservableUseCase<Category, GetCategoryById.Params?>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Params?): Observable<Category> {
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