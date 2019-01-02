package com.thomaskioko.paybillmanager.domain.interactor.bills

import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.usecase.ObservableUseCase
import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.domain.repository.BillsRepository
import io.reactivex.Observable
import javax.inject.Inject

open class GetBillByIds @Inject constructor(
        private val billsRepository: BillsRepository,
        postExecutionThread: PostExecutionThread
) : ObservableUseCase<Bill, GetBillByIds.Params?>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Params?): Observable<Bill> {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return billsRepository.getBillByIds(params.billId, params.categoryId)
    }


    data class Params constructor(val billId: String, val categoryId: String) {
        companion object {
            fun forBillByIds(billId: String, categoryId: String): Params {
                return Params(billId, categoryId)
            }
        }
    }

}