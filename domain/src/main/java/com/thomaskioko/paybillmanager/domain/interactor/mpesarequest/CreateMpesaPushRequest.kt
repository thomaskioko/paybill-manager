package com.thomaskioko.paybillmanager.domain.interactor.mpesarequest

import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.executor.ThreadExecutor
import com.thomaskioko.paybillmanager.domain.model.JengaMpesaPushResponse
import com.thomaskioko.paybillmanager.domain.model.mpesa.MpesaPushRequest
import com.thomaskioko.paybillmanager.domain.repository.MpesaRequestRepository
import com.thomaskioko.paybillmanager.domain.usecase.FlowableUseCase
import io.reactivex.Flowable
import javax.inject.Inject

open class CreateMpesaPushRequest @Inject constructor(
        val repository: MpesaRequestRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
) : FlowableUseCase<JengaMpesaPushResponse, CreateMpesaPushRequest.Params>(threadExecutor, postExecutionThread) {


    public override fun buildUseCaseObservable(params: Params?): Flowable<JengaMpesaPushResponse> {
        if (params == null) throw IllegalArgumentException("Params can't be null")
        return repository.createMpesaPushRequest(params.mpesaPushRequest)
    }


    data class Params constructor(val mpesaPushRequest: MpesaPushRequest) {
        companion object {
            fun forCreateMpesaPush(mpesaPushRequest: MpesaPushRequest): Params {
                return Params(mpesaPushRequest)
            }
        }
    }
}