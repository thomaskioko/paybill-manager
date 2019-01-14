package com.thomaskioko.paybillmanager.data.repository.jengatoken

import com.thomaskioko.paybillmanager.data.model.JengaTokenEntity
import com.thomaskioko.paybillmanager.data.model.MpesaPushResponseEntity
import io.reactivex.Flowable

interface JengaRemote {

    fun getJengaToken(): Flowable<JengaTokenEntity>

    fun getMpesaPushResponse(bearerToken: String, signature: String)
            : Flowable<MpesaPushResponseEntity>
}