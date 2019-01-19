package com.thomaskioko.paybillmanager.domain.interactor.billcategory

import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.executor.ThreadExecutor
import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.domain.repository.BillCategoryRepository
import com.thomaskioko.paybillmanager.domain.usecase.FlowableUseCase
import io.reactivex.Flowable
import javax.inject.Inject

open class GetBillsByCategoryId @Inject constructor(
        private val repository: BillCategoryRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
) : FlowableUseCase<List<Bill>, GetBillsByCategoryId.Params?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Params?): Flowable<List<Bill>> {
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