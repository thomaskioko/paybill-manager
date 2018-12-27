package com.thomaskioko.paybillmanager.domain.interactor.billcategory

import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.model.BillCategory
import com.thomaskioko.paybillmanager.domain.repository.BillCategoryRepository
import com.thomaskioko.paybillmanager.domain.usecase.ObservableUseCase
import io.reactivex.Observable
import javax.inject.Inject

open class GetBillCategory @Inject constructor(
        private val repository: BillCategoryRepository,
        postExecutionThread: PostExecutionThread
) : ObservableUseCase<BillCategory, GetBillCategory.Params?>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Params?): Observable<BillCategory> {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return repository.getBillCategory(params.billId, params.categoryId)
    }


    data class Params constructor(val billId: String, val categoryId: String) {
        companion object {
            fun forBillCategory(billId: String, categoryId: String): Params {
                return Params(billId, categoryId)
            }
        }
    }

}