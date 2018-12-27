package com.thomaskioko.paybillmanager.domain.interactor.category

import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.usecase.ObservableUseCase
import com.thomaskioko.paybillmanager.domain.model.Category
import com.thomaskioko.paybillmanager.domain.repository.CategoryRepository
import io.reactivex.Observable
import javax.inject.Inject

open class GetCategories @Inject constructor(
        private val categoryRepository: CategoryRepository,
        postExecutionThread: PostExecutionThread
) : ObservableUseCase<List<Category>, Nothing?>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Nothing?): Observable<List<Category>> {
        return categoryRepository.getCategories()
    }

}