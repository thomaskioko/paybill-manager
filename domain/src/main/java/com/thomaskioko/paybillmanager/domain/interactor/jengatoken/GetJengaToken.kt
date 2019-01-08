package com.thomaskioko.paybillmanager.domain.interactor.jengatoken

import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.executor.ThreadExecutor
import com.thomaskioko.paybillmanager.domain.model.JengaToken
import com.thomaskioko.paybillmanager.domain.repository.JengaTokenRepository
import com.thomaskioko.paybillmanager.domain.usecase.FlowableUseCase
import io.reactivex.Flowable
import javax.inject.Inject

open class GetJengaToken @Inject constructor(
        private val jengaTokenRepository: JengaTokenRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
) : FlowableUseCase<JengaToken, Nothing?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Nothing?): Flowable<JengaToken> {
        return jengaTokenRepository.getJengaToken()
    }
}