package com.thomaskioko.paybillmanager.data.repository.mpesapush

import com.thomaskioko.paybillmanager.data.model.MpesaPushRequestEntity
import com.thomaskioko.paybillmanager.data.model.MpesaPushResponseEntity
import com.thomaskioko.paybillmanager.domain.model.mpesa.MpesaPushRequest
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Interface defining methods for the caching of MpesaPushResponse. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface MpesaPushCache {

    fun clearMpesaPushRequests(): Completable

    fun getMpesaStkPushRequest(mpesaPusRequestEntity: MpesaPushRequest)
            : Flowable<MpesaPushResponseEntity>

    fun saveMpesaPushResponse(mpesaPusRequestEntity: MpesaPushResponseEntity): Completable

    fun isStkResponseCached(): Single<Boolean>

}