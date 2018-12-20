package com.thomaskioko.paybillmanager.domain.interactor.bills

import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.interactor.ObservableUseCase
import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.domain.repository.BillsRepository
import io.reactivex.Observable
import javax.inject.Inject

open class GetBillById @Inject constructor(
        private val billsRepository: BillsRepository,
        postExecutionThread: PostExecutionThread
) : ObservableUseCase<Bill, GetBillById.Params?>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Params?): Observable<Bill> {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return billsRepository.getBillById(params.billId)
    }


    data class Params constructor(val billId: String) {
        companion object {
            fun forBill(billId: String): Params {
                return Params(billId)
            }
        }
    }

}