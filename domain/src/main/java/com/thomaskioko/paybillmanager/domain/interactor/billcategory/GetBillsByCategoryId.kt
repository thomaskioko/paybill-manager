package com.thomaskioko.paybillmanager.domain.interactor.billcategory

import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.domain.repository.BillCategoryRepository
import com.thomaskioko.paybillmanager.domain.usecase.ObservableUseCase
import io.reactivex.Observable
import javax.inject.Inject

open class GetBillsByCategoryId @Inject constructor(
        private val repository: BillCategoryRepository,
        postExecutionThread: PostExecutionThread
) : ObservableUseCase<List<Bill>, GetBillsByCategoryId.Params?>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Params?): Observable<List<Bill>> {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return repository.getBillsByCategoryId(params.categoryId)
    }


    data class Params constructor(val categoryId: String) {
        companion object {
            fun forBillsByCategoryId(categoryId: String): Params {
                return Params(categoryId)
            }
        }
    }

}