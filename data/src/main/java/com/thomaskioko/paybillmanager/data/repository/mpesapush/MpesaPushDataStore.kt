package com.thomaskioko.paybillmanager.data.repository.mpesapush

import com.thomaskioko.paybillmanager.data.model.MpesaPushRequestEntity
import com.thomaskioko.paybillmanager.data.model.MpesaPushResponseEntity
import com.thomaskioko.paybillmanager.domain.model.MpesaPushResponse
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Interface used to help communicate with external sources. They act as an access point.
 * Allows us to avoid having direct access to source implementation
 */
interface MpesaPushDataStore {

    fun saveMpesaPushResponse(mpesaPushResponseEntity: MpesaPushResponseEntity): Completable

    fun createMpesaPushRequest(mpesaPushRequestEntity: MpesaPushRequestEntity): Flowable<MpesaPushResponseEntity>

}