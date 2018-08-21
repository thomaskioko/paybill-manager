package com.thomaskioko.paybillmanager.domain.bills

import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.interactor.ObservableUseCase
import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.domain.repository.BillsRepository
import io.reactivex.Observable
import javax.inject.Inject

open class GetBills @Inject constructor(
        private val billsRepository: BillsRepository,
        postExecutionThread: PostExecutionThread
) : ObservableUseCase<List<Bill>, Nothing?>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Nothing?): Observable<List<Bill>> {
        return billsRepository.getBills()
    }

}