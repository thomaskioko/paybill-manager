package com.thomaskioko.paybillmanager.domain.bills

import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.interactor.CompletableUseCase
import com.thomaskioko.paybillmanager.domain.repository.BillsRepository
import io.reactivex.Completable
import javax.inject.Inject

open class UpdateBill @Inject constructor(
        private val billsRepository: BillsRepository,
        postExecutionThread: PostExecutionThread
) : CompletableUseCase<UpdateBill.Params>(postExecutionThread) {

    public override fun buildUseCaseCompletable(params: Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return billsRepository.updateBill(params.billId)
    }

    data class Params constructor(val billId: String) {
        companion object {
            fun forBill(billId: String): Params {
                return Params(billId)
            }
        }
    }

}