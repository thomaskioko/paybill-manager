package com.thomaskioko.paybillmanager.data.repository.mpesapush

import com.thomaskioko.paybillmanager.data.model.MpesaPushResponseEntity
import com.thomaskioko.paybillmanager.domain.model.mpesa.MpesaPushRequest
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Interface defining methods for the data operations related to MpesaPush.
 * This is to be implemented by external data source layers, setting the requirements for the
 * operations that need to be implemented
 */
interface MpesaPushDataStore {

    fun clearMpesaPushRequests(): Completable

    fun saveMpesaPushResponse(mpesaPushResponse: MpesaPushResponseEntity): Completable

    fun getMpesaStkPushRequest(bearerToken: String, signaturePayload: String): Flowable<MpesaPushResponseEntity>

    fun getMpesaStkPushRequests(): Flowable<List<MpesaPushResponseEntity>>

    fun isStkResponseCached(transactionReference: String): Single<Boolean>

}