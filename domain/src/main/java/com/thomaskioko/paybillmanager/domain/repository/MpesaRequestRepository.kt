package com.thomaskioko.paybillmanager.domain.repository

import com.thomaskioko.paybillmanager.domain.model.MpesaPushResponse
import com.thomaskioko.paybillmanager.domain.model.mpesa.MpesaPushRequest
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Interface defining methods for how the data layer can pass data to and from the Domain layer.
 * This is to be implemented by the data layer, setting the requirements for the
 * operations that need to be implemented
 */
interface MpesaRequestRepository {

    fun clearMpesaPushRequests(): Completable

    fun getMpesaStkPush(bearerToken: String, mpesaPushRequest: MpesaPushRequest): Flowable<MpesaPushResponse>

    fun getMpesaPushResponse(): Flowable<List<MpesaPushResponse>>

    fun saveMpesaPushResponse(mpesaPushResponse: MpesaPushResponse): Completable
}