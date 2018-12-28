package com.thomaskioko.paybillmanager.domain.interactor.billcategory

import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.model.BillCategory
import com.thomaskioko.paybillmanager.domain.repository.BillCategoryRepository
import com.thomaskioko.paybillmanager.domain.usecase.CompletableUseCase
import io.reactivex.Completable
import javax.inject.Inject

open class CreateBillsCategory @Inject constructor(
        private val repository: BillCategoryRepository,
        postExecutionThread: PostExecutionThread
) : CompletableUseCase<CreateBillsCategory.Params>(postExecutionThread) {

    public override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return repository.createBillCategory(params.billCategory)
    }

    data class Params constructor(val billCategory: BillCategory) {
        companion object {
            fun forBillCategory(billCategory: BillCategory): Params {
                return Params(billCategory)
            }
        }
    }
}