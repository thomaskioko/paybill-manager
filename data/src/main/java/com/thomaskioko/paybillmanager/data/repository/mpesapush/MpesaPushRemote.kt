package com.thomaskioko.paybillmanager.data.repository.mpesapush

import com.thomaskioko.paybillmanager.data.model.MpesaPushRequestEntity
import com.thomaskioko.paybillmanager.data.model.MpesaPushResponseEntity
import com.thomaskioko.paybillmanager.domain.model.mpesa.MpesaPushRequest
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Interface defining methods for the caching of MpesaPush. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface MpesaPushRemote {

    fun getMpesaStkPushRequest(mpesaPushRequestEntity: MpesaPushRequest)
            : Flowable<MpesaPushResponseEntity>

}