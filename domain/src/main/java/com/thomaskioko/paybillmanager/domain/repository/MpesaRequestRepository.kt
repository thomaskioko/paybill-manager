package com.thomaskioko.paybillmanager.domain.repository

import com.thomaskioko.paybillmanager.domain.model.MpesaPushResponse
import com.thomaskioko.paybillmanager.domain.model.mpesa.MpesaPushRequest
import io.reactivex.Flowable

interface MpesaRequestRepository {
    fun createMpesaPushRequest(mpesaPushRequest: MpesaPushRequest): Flowable<MpesaPushResponse>
}