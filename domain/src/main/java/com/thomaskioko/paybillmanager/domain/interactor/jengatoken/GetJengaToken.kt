package com.thomaskioko.paybillmanager.domain.interactor.jengatoken

import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.interactor.ObservableUseCase
import com.thomaskioko.paybillmanager.domain.model.JengaToken
import com.thomaskioko.paybillmanager.domain.repository.JengaTokenRepository
import io.reactivex.Observable
import javax.inject.Inject

open class GetJengaToken @Inject constructor(
        private val jengaTokenRepository: JengaTokenRepository,
        postExecutionThread: PostExecutionThread
) : ObservableUseCase<JengaToken, Nothing?>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Nothing?): Observable<JengaToken> {
        return jengaTokenRepository.getJengaToken()
    }
}