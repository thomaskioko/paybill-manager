package com.thomaskioko.paybillmanager.data.repository.mpesapush

import com.thomaskioko.paybillmanager.data.model.MpesaPushRequestEntity
import com.thomaskioko.paybillmanager.data.model.MpesaPushResponseEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Defines abstract methods to be implemented by the cash module.
 */

interface MpesaPushRequestCache {

    fun saveMpesaPushRequest(mpesaPusRequestEntity: MpesaPushRequestEntity): Flowable<MpesaPushResponseEntity>

    fun saveMpesaRequestResponse(mpesaPusRequestEntity: MpesaPushResponseEntity): Completable

    fun isMpesaPushResponseCached(): Single<Boolean>

}