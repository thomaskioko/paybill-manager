package com.thomaskioko.paybillmanager.data.repository.jengatoken

import com.thomaskioko.paybillmanager.data.model.JengaTokenEntity
import com.thomaskioko.paybillmanager.data.model.MpesaPushResponseEntity
import com.thomaskioko.paybillmanager.domain.model.mpesa.MpesaPushRequest
import io.reactivex.Flowable

interface JengaRemote {

    fun getJengaToken(): Flowable<JengaTokenEntity>

    fun getMpesaPushResponse(bearerToken: String, signature: String, mpesaPushRequest: MpesaPushRequest)
            : Flowable<MpesaPushResponseEntity>
}