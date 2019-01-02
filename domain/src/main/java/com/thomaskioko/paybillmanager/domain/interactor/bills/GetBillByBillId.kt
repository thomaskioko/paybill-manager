package com.thomaskioko.paybillmanager.domain.interactor.bills

import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.usecase.ObservableUseCase
import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.domain.repository.BillsRepository
import io.reactivex.Observable
import javax.inject.Inject

open class GetBillByBillId @Inject constructor(
        private val billsRepository: BillsRepository,
        postExecutionThread: PostExecutionThread
) : ObservableUseCase<Bill, GetBillByBillId.Params?>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Params?): Observable<Bill> {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return billsRepository.getBillByBillId(params.billId)
    }


    data class Params constructor(val billId: String) {
        companion object {
            fun forBill(billId: String): Params {
                return Params(billId)
            }
        }
    }

}