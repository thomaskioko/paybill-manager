package com.thomaskioko.paybillmanager.domain.interactor.billcategory

import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.executor.ThreadExecutor
import com.thomaskioko.paybillmanager.domain.model.Category
import com.thomaskioko.paybillmanager.domain.repository.BillCategoryRepository
import com.thomaskioko.paybillmanager.domain.usecase.FlowableUseCase
import io.reactivex.Flowable
import javax.inject.Inject

open class GetCategoryByBillId @Inject constructor(
        private val repository: BillCategoryRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
) : FlowableUseCase<Category, GetCategoryByBillId.Params?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Params?): Flowable<Category> {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return repository.getCategoryByBillId(params.billId)
    }


    data class Params constructor(val billId: String) {
        companion object {
            fun forCategoryByBillId(billId: String): Params {
                return Params(billId)
            }
        }
    }

}