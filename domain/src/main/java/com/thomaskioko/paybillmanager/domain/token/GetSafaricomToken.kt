package com.thomaskioko.paybillmanager.domain.token

import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.interactor.ObservableUseCase
import com.thomaskioko.paybillmanager.domain.model.SafaricomToken
import com.thomaskioko.paybillmanager.domain.repository.TokenRepository
import io.reactivex.Observable
import javax.inject.Inject

open class GetSafaricomToken @Inject constructor(
        private val tokenRepository: TokenRepository,
        postExecutionThread: PostExecutionThread
) : ObservableUseCase<SafaricomToken, Nothing?>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Nothing?): Observable<SafaricomToken> {
        return tokenRepository.getToken()
    }
}