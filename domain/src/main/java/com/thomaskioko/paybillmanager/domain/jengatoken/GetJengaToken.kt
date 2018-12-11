package com.thomaskioko.paybillmanager.domain.jengatoken

import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.interactor.ObservableUseCase
import com.thomaskioko.paybillmanager.domain.model.JengaToken
import com.thomaskioko.paybillmanager.domain.repository.JengaTokenRepository
import io.reactivex.Observable
import javax.inject.Inject

open class GetJengaToken @Inject constructor(
        private val jengaTokenRepository: JengaTokenRepository,
        postExecutionThread: PostExecutionThread
) : ObservableUseCase<JengaToken, GetJengaToken.Params>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Params?): Observable<JengaToken> {
        return jengaTokenRepository.getJengaToken()
    }

    data class Params constructor(val userName: String, val password: String) {

        companion object {
            fun forJengaToken(userName: String, password: String): Params {
                return Params(userName, password)
            }
        }
    }
}