package com.thomaskioko.paybillmanager.data.repository.mpesapush

import com.thomaskioko.paybillmanager.data.model.MpesaPushRequestEntity
import com.thomaskioko.paybillmanager.data.model.MpesaPushResponseEntity
import com.thomaskioko.paybillmanager.domain.model.MpesaPushResponse
import io.reactivex.Flowable

interface MpesaPushRequestRemote {

    fun createMpesaPushRequest(mpesaPushRequestEntity: MpesaPushRequestEntity): Flowable<MpesaPushResponseEntity>

}