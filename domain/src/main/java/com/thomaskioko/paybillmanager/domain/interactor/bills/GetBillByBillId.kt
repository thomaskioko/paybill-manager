package com.thomaskioko.paybillmanager.domain.interactor.bills

import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.executor.ThreadExecutor
import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.domain.repository.BillsRepository
import com.thomaskioko.paybillmanager.domain.usecase.FlowableUseCase
import io.reactivex.Flowable
import javax.inject.Inject

open class GetBillByBillId @Inject constructor(
        private val billsRepository: BillsRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
) : FlowableUseCase<Bill, GetBillByBillId.Params?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Params?): Flowable<Bill> {
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